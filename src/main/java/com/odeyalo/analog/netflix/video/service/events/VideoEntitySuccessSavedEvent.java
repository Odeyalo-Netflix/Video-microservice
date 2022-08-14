package com.odeyalo.analog.netflix.video.service.events;

import com.odeyalo.analog.netflix.video.entity.Video;

public class VideoEntitySuccessSavedEvent extends Event {
    private final Video video;

    public VideoEntitySuccessSavedEvent(String eventId, Video video) {
        super(eventId);
        this.video = video;
    }

    public Video getVideo() {
        return video;
    }

    @Override
    public String toString() {
        return "VideoEntitySuccessSavedEvent{" +
                "eventId='" + eventId + '\'' +
                ", video=" + video +
                '}';
    }
}
