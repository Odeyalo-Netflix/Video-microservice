package com.odeyalo.analog.netflix.video.support;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Component
public class SimpleFileReader implements FileReader {

    @Override
    public byte[] readFile(Path path) throws IOException {
        return Files.readAllBytes(path);
    }
}
