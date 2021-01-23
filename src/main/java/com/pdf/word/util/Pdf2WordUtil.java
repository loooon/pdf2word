package com.pdf.word.util;

import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * @author michael chen
 * @version 1.0
 * @description
 * @date 2021/1/23 16:23
 */
public class Pdf2WordUtil {
    public static String pdfTransferString(MultipartFile multipartFile) {
        String fileName = multipartFile.getOriginalFilename();
        // 获取文件后缀
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        File filePdf = null;
        File fileWord = null;
        try {
            filePdf = File.createTempFile(UuidUtils.createUUID(), prefix);
            // MultipartFile to File
            multipartFile.transferTo(filePdf);
            StringBuffer wordFileName = new StringBuffer();
            wordFileName.append(fileName, 0, fileName.lastIndexOf("."))
                    .append(UuidUtils.createUUID())
                    .append(FilesEnum.DOC.getDesc());
            fileWord = new File(wordFileName.toString());
            if (!fileWord.exists()) {
                fileWord.createNewFile();
            }
            PDFParser pdfParser = new PDFParser(new RandomAccessFile(filePdf, "r"));
            pdfParser.parse();
            PDDocument pdDocument = new PDDocument(pdfParser.getDocument());
            PDFTextStripper stripper = new PDFTextStripper();
            return stripper.getText(pdDocument);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fileWord != null && fileWord.exists()) {
                deleteFile(fileWord);
            }
            if (filePdf != null && filePdf.exists()) {
                deleteFile(filePdf);
            }
        }
        return null;
    }

    /**
     * 删除
     *
     * @param files
     */
    public static void deleteFile(File... files) {
        for (File file : files) {
            if (file.exists()) {
                file.delete();
            }
        }
    }

}
