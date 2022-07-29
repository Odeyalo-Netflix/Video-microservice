package com.odeyalo.analog.netflix.video.exceptions;

public class WorkflowStepException extends RuntimeException {
    public WorkflowStepException(String message) {
        super(message);
    }

    public WorkflowStepException(String message, Throwable cause) {
        super(message, cause);
    }
}
