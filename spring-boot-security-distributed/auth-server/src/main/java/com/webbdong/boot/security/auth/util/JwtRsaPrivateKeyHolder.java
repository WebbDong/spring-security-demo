package com.webbdong.boot.security.auth.util;

import com.webbdong.boot.security.common.util.RsaUtils;

import java.security.PrivateKey;

/**
 * @author: Webb Dong
 * @date: 2021-08-02 11:23 AM
 */
public enum JwtRsaPrivateKeyHolder {

    INSTANCE;

    private static final String JWT_PRIVATE_KEY_FILE_NAME = "jwt_rsa_private_key.pem";

    private static final PrivateKey jwtPrivateKey;

    static {
        try {
            jwtPrivateKey = RsaUtils.getPrivateKeyFromClassPath(JWT_PRIVATE_KEY_FILE_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PrivateKey getJwtPrivateKey() {
        return jwtPrivateKey;
    }

}
