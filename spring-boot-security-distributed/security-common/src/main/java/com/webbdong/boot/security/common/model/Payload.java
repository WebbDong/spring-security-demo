package com.webbdong.boot.security.common.model;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * @author: Webb Dong
 * @date: 2021-07-29 6:14 PM
 */
@Data
@SuperBuilder
public class Payload<T> {

    /**
     * JWT BODY ID
     */
    private String id;

    /**
     * BODY 数据
     */
    private T data;

    /**
     * 过期时间
     */
    private Date expiration;

}
