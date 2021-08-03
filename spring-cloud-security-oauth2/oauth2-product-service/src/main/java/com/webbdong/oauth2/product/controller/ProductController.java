package com.webbdong.oauth2.product.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Webb Dong
 * @date 2021-08-03 2:08 PM
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @GetMapping("/findAll")
    public String findAll() {
        return "查询产品列表成功！";
    }

}
