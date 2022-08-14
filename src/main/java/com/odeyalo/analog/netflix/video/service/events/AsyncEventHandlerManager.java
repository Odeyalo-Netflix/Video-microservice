package com.odeyalo.analog.netflix.video.service.events;

import com.odeyalo.analog.netflix.video.service.events.handler.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * A simple manager that provides functionality to event firing in async mode
 */
@Component
public class AsyncEventHandlerManager implements EventHandlerManager {
    private final EventHandlerContainer container;
    private final Logger logger = LoggerFactory.getLogger(AsyncEventHandlerManager.class);

    @Autowired
    public AsyncEventHandlerManager(EventHandlerContainer container) {
        this.container = container;
    }

    @Async
    public void fireEvent(String eventKey, Event event) {
        this.logger.info("Staring event handling, event class: {}, event id: {}, event key: {}", event.getClass(), event.getEventId(), eventKey);
        processEvent(eventKey, event);
    }

    protected void processEvent(String eventKey, Event event) {
        List<EventHandler> eventHandlers = this.container.eventHandlers.get(eventKey);
        for (EventHandler eventHandler : eventHandlers) {
            eventHandler.processEvent(event);
        }
    }
}
