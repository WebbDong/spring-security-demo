package com.webbdong.boot.security.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON 工具类
 * @author: Webb Dong
 * @date: 2021-07-29 2:29 PM
 */
public class JsonUtils {

    private static final ObjectMapper OBJ_MAPPER = new ObjectMapper();

    /**
     * 将对象转换成 JSON 字符串
     */
    public static String toJson(Object obj) throws JsonProcessingException {
        return OBJ_MAPPER.writeValueAsString(obj);
    }

    /**
     * 将 JSON 字符串转换成对象
     */
    public static <T> T parseObj(String json, Class<T> clazz) throws JsonProcessingException {
        return OBJ_MAPPER.readValue(json, clazz);
    }

    /**
     * 将 JSON 字符串转换成复杂对象
     */
    public static <T> T parseObj(String json, TypeReference<T> typeReference) throws JsonProcessingException {
        return OBJ_MAPPER.readValue(json, typeReference);
    }

    private JsonUtils() {}

}
