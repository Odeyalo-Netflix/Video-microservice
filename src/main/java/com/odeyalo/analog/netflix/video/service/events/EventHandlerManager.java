package com.odeyalo.analog.netflix.video.service.events;

public interface EventHandlerManager {

    /**
     * Fires specific event and runs EventHandler.processEvent
     * @param eventKey - unique event key
     * @param event    - event to process
     */
    void fireEvent(String eventKey, Event event);

}
