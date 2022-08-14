package com.odeyalo.analog.netflix.video.service.events.handler;

import com.odeyalo.analog.netflix.video.service.events.Event;

public interface EventHandler {

    void processEvent(Event event);

}
