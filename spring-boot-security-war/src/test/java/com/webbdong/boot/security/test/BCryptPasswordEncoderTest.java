package com.webbdong.boot.security.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Webb Dong
 * @date: 2021-07-29 12:36 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BCryptPasswordEncoderTest {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Test
    public void test() {
        System.out.println(passwordEncoder.encode("123456"));
        System.out.println(passwordEncoder.matches("123456", "$2a$10$4dCWPXBpVQ61ByHr.uiGPORA3xnLe1XBfnO6ONMN5CLVYFL.JSAJW"));
    }

}
