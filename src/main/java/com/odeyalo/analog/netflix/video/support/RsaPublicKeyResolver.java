package com.odeyalo.analog.netflix.video.support;

import com.odeyalo.analog.netflix.video.exceptions.KeyConstructionException;

import java.security.PublicKey;

public interface RsaPublicKeyResolver {

    PublicKey getPublicKey() throws KeyConstructionException;

}
