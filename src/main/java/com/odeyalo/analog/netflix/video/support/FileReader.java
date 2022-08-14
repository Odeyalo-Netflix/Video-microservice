package com.odeyalo.analog.netflix.video.support;

import java.io.IOException;
import java.nio.file.Path;

public interface FileReader {

    byte[] readFile(Path path) throws IOException;
}
