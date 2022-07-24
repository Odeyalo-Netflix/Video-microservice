package com.odeyalo.analog.netflix.video.service.video.events;

public abstract class Event {
    protected String eventId;

    public Event() {}

    public Event(String eventId) {
        this.eventId = eventId;
    }

    public String getEventId() {
        return eventId;
    }
}
