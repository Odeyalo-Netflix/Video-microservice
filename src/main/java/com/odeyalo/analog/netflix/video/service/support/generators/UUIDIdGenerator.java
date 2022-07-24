package com.odeyalo.analog.netflix.video.service.support.generators;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDIdGenerator implements IdGenerator {
    @Override
    public String generateId() {
        return UUID.randomUUID().toString();
    }
}
