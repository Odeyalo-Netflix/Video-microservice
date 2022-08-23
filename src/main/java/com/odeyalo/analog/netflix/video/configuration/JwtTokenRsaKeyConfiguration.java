package com.odeyalo.analog.netflix.video.configuration;

import com.odeyalo.analog.netflix.video.support.*;
import com.odeyalo.netflix.jwt.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.PublicKey;

@Configuration
public class JwtTokenRsaKeyConfiguration {
    private final Logger logger = LoggerFactory.getLogger(JwtTokenRsaKeyConfiguration.class);

    @Bean
    public FileReader fileReader() {
        return new SimpleFileReader();
    }

    @Bean
    public RsaPublicKeyResolver rsaPublicKeyResolver(
            @Value("${app.security.rsa.keys.public.filename}") String fileName,
            FileReader fileReader) {
        return new FileRsaPublicKeyResolver(fileName, fileReader);
    }

    @Bean
    public JwtTokenParser rsaPublicKeyJwtTokenParser() {
        return new RsaPublicKeyJwtTokenParser();
    }

    @Bean
    Void registryPublicKey(RsaPublicKeyResolver rsaPublicKeyResolver) throws KeyConstructionException {
        PublicKey publicKey = rsaPublicKeyResolver.getPublicKey();
        this.logger.info("Public key was set with size: {}", publicKey.getEncoded().length);
        RsaTokenHolder.setPublicKey(publicKey);
        return null;
    }
}
