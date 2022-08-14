package com.odeyalo.analog.netflix.video.support;

import com.odeyalo.analog.netflix.video.exceptions.KeyConstructionException;

import java.io.IOException;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class FileRsaPublicKeyResolver implements RsaPublicKeyResolver {
    private final String fileName;
    private final FileReader fileReader;

    public FileRsaPublicKeyResolver(String fileName, FileReader fileReader) {
        this.fileName = fileName;
        this.fileReader = fileReader;
    }

    @Override
    public PublicKey getPublicKey() throws KeyConstructionException {
        try {
            byte[] bytes = this.fileReader.readFile(Paths.get(fileName));
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePublic(new X509EncodedKeySpec(bytes));
        } catch (IOException | InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new KeyConstructionException(String.format("Cannot construct a public key from file: %s.", fileName), e);
        }
    }
}
