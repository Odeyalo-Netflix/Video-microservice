package com.odeyalo.analog.netflix.video.service.video.listeners;

import com.odeyalo.analog.netflix.video.service.events.Event;

public interface EventListener<T extends Event> {

    void listenEvent(T event);

}
