package com.webbdong.boot.security.common.util;

import java.security.PublicKey;

/**
 * @author: Webb Dong
 * @date: 2021-07-29 7:41 PM
 */
public enum JwtRsaPublicKeyHolder {

    INSTANCE;

    private static final String JWT_PUBLIC_KEY_FILE_NAME = "jwt_rsa_public_key.pem";

    private static final PublicKey jwtPublicKey;

    static {
        try {
            jwtPublicKey = RsaUtils.getPublicKeyFromClassPath(JWT_PUBLIC_KEY_FILE_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PublicKey getJwtPublicKey() {
        return jwtPublicKey;
    }

}
