package com.odeyalo.analog.netflix.video.service.events;

import com.odeyalo.analog.netflix.video.service.events.handler.EventHandler;

public interface EventHandlerRegistry {

    void registerEventHandler(String eventKey, EventHandler eventHandler);

    boolean containsEventHandler(String eventKey);

    void removeEventHandler(String eventKey);
}
