package com.odeyalo.analog.netflix.video.service.support;

/**
 * Convert one parameter to another
 * @param <F> - from object
 * @param <T> - to object
 */
public interface ResponseConverter<F, T> {

    T convert(F from);
}
