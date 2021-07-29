package com.webbdong.boot.security.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author: Webb Dong
 * @date: 2021-07-28 9:02 PM
 */
@Configuration
@MapperScan(basePackages = "com.webbdong.boot.security.dao")
public class MyBatisConfig {
}
