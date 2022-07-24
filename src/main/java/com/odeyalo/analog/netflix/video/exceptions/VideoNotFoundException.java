package com.odeyalo.analog.netflix.video.exceptions;

public class VideoNotFoundException extends RuntimeException {
    public VideoNotFoundException() {
        super();
    }

    public VideoNotFoundException(String message) {
        super(message);
    }

    public VideoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
