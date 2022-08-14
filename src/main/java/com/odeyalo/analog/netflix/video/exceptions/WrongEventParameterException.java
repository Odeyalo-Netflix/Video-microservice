package com.odeyalo.analog.netflix.video.exceptions;

public class WrongEventParameterException extends RuntimeException {
    public WrongEventParameterException(String message) {
        super(message);
    }

    public WrongEventParameterException(String message, Throwable cause) {
        super(message, cause);
    }
}
