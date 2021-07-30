package com.webbdong.boot.security.common.model;

import lombok.Builder;
import lombok.Data;

/**
 * @author: Webb Dong
 * @date: 2021-07-30 10:23 AM
 */
@Data
@Builder
public class Response<T> {

    private int code;

    private String msg;

    private T data;

}
