package com.pdf.word.repository;


import com.pdf.word.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author michael chen
 * @version 1.0
 * @description 用户表
 * 参数一 T :当前需要映射的实体
 * 参数二 ID :当前映射的实体中的OID的类型
 * @date 2021/1/23 16:19
 */
public interface UserRepository extends JpaRepository<User, Integer> {

}