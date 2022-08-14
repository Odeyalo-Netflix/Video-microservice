package com.odeyalo.analog.netflix.video.support;

import java.security.PublicKey;

public class RsaTokenHolder {
    private static PublicKey publicKey;

    public static PublicKey getPublicKey() {
        return publicKey;
    }

    public static void setPublicKey(PublicKey publicKey) {
        RsaTokenHolder.publicKey = publicKey;
    }
}
