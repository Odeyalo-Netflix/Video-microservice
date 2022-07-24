package com.odeyalo.analog.netflix.video.exceptions;

import java.text.MessageFormat;

public class ExceptionUtils {

    public static String toMessage(String msg, Object... args) {
        return MessageFormat.format(msg, args);
    }
}
