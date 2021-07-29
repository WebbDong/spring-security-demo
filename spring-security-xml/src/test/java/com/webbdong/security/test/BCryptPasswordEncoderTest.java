package com.webbdong.security.test;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author Webb Dong
 * @date 2021-07-26 2:18 AM
 */
public class BCryptPasswordEncoderTest {

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        System.out.println(encoder.encode("123456"));
        System.out.println(encoder.matches("123456", "$2a$10$Kp8GcvtYNTlmE5aGJLPt9u.2dvHPDGI1OjNAvzVJ9jqlZ5N4wdtIa"));
        System.out.println(encoder.matches("123456", "$2a$10$R5MF7TKALZwE.yp1htK3V.uv6fOZCrehlETgNgRZHySaCNnl5efoO"));
    }

}
