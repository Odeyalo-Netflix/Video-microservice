package com.odeyalo.analog.netflix.video.service.video.listeners;

import com.odeyalo.analog.netflix.video.service.events.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractEventListener<T extends Event> implements EventListener<T> {
    protected final Logger logger = LoggerFactory.getLogger(AbstractEventListener.class);
}
