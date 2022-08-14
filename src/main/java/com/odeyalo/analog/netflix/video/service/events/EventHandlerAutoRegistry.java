package com.odeyalo.analog.netflix.video.service.events;

import com.odeyalo.analog.netflix.video.service.events.handler.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Auto registry event handler in container, using spring framework for it. It can be overridden and used with reflection API or etc
 */
public interface EventHandlerAutoRegistry {

    String getEventKey();

    EventHandler getEventHandler();

    @Autowired
    default void autoRegistryEventHandler(EventHandlerRegistry registry) {
        registry.registerEventHandler(getEventKey(), getEventHandler());
    }
}
