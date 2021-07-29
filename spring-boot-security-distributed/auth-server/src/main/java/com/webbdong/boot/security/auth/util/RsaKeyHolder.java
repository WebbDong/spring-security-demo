package com.webbdong.boot.security.auth.util;

import com.webbdong.boot.security.common.util.RsaUtils;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author: Webb Dong
 * @date: 2021-07-29 7:41 PM
 */
public enum RsaKeyHolder {

    INSTANCE;

    private static final String JWT_PRIVATE_KEY_FILE_NAME = "jwt_rsa_private_key.pem";

    private static final String JWT_PUBLIC_KEY_FILE_NAME = "jwt_rsa_public_key.pem";

    private static final PrivateKey jwtPrivateKey;

    private static final PublicKey jwtPublicKey;

    static {
        try {
            jwtPrivateKey = RsaUtils.getPrivateKeyFromClassPath(JWT_PRIVATE_KEY_FILE_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        try {
            jwtPublicKey = RsaUtils.getPublicKeyFromClassPath(JWT_PUBLIC_KEY_FILE_NAME);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PrivateKey getJwtPrivateKey() {
        return jwtPrivateKey;
    }

    public PublicKey getJwtPublicKey() {
        return jwtPublicKey;
    }

}
