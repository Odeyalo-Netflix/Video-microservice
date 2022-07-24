package com.odeyalo.analog.netflix.video.exceptions;

public class VideoUploadException extends Exception {

    public VideoUploadException() {
        super();
    }

    public VideoUploadException(String message) {
        super(message);
    }

    public VideoUploadException(String message, Throwable cause) {
        super(message, cause);
    }

    public VideoUploadException(String message, Object... args) {
        super(ExceptionUtils.toMessage(message, args));
    }

    public VideoUploadException(String message, Throwable cause, Object... args) {
        super(ExceptionUtils.toMessage(message, args), cause);
    }
}
