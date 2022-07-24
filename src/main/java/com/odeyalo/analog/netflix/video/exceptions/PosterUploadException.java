package com.odeyalo.analog.netflix.video.exceptions;

public class PosterUploadException extends Exception {
    public PosterUploadException() {
        super();
    }

    public PosterUploadException(String message) {
        super(message);
    }

    public PosterUploadException(String message, Throwable cause) {
        super(message, cause);
    }
}
