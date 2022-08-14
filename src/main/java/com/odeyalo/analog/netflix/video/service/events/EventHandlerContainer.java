package com.odeyalo.analog.netflix.video.service.events;

import com.odeyalo.analog.netflix.video.service.events.handler.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class EventHandlerContainer implements EventHandlerRegistry {
    protected Map<String, List<EventHandler>> eventHandlers;
    protected final Logger logger = LoggerFactory.getLogger(EventHandlerContainer.class);

    public EventHandlerContainer() {
        this.eventHandlers = new HashMap<>();
    }

    /**
     *
     * @param eventHandlers - map with event handlers
     */
    public EventHandlerContainer(Map<String, List<EventHandler>> eventHandlers) {
        this.eventHandlers = eventHandlers;
    }


    @Override
    public void registerEventHandler(String eventKey, EventHandler eventHandler) {
        List<EventHandler> eventHandlersValue = this.eventHandlers.get(eventKey);
        if (eventHandlersValue == null) {
            eventHandlersValue = new ArrayList<>();
        }
        eventHandlersValue.add(eventHandler);
        eventHandlers.put(eventKey, eventHandlersValue);
        this.logger.info("Registered in container: {} with key: {}", eventHandler.getClass(), eventKey);
    }

    @Override
    public boolean containsEventHandler(String eventKey) {
        return this.eventHandlers.containsKey(eventKey);
    }

    @Override
    public void removeEventHandler(String eventKey) {
        this.eventHandlers.remove(eventKey);
    }
}
