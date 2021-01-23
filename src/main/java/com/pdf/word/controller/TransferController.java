package com.pdf.word.controller;


import com.pdf.word.util.Pdf2WordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

/**
 * @author michael chen
 * @version 1.0
 * @description pdf转word
 * @date 2021/1/23 16:18
 */
@RestController
@RequestMapping("/transfer")
public class TransferController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping("/test")
    public String test() {
        return "success";
    }

    @PostMapping("/pdf2word")
    public byte[] uploadPdfDownloadWord(@RequestParam(value = "file") MultipartFile file,
                                        HttpServletResponse response
    ) {
        try {
            String fileWord = Pdf2WordUtil.pdfTransferString(file);
            String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf(".pdf"));
            StringBuffer buff = new StringBuffer();
            buff.append(fileWord);
            fileName = URLEncoder.encode(fileName, "UTF-8");
//            response.setContentType("application/octet-stream");
            response.setContentType("application/msword");
            response.setHeader("Content-disposition", "attachment;filename=" + fileName + ".doc");
            return buff.toString().getBytes();
        } catch (Exception e) {
            logger.error("pdf转word异常,{}", e);
        }
        return null;
    }
}
