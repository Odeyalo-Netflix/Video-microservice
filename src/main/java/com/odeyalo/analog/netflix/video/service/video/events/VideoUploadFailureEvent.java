package com.odeyalo.analog.netflix.video.service.video.events;

public class VideoUploadFailureEvent extends Event {
    protected String videoId;
    protected String message;

    public VideoUploadFailureEvent() {
    }

    public VideoUploadFailureEvent(String eventId, String videoId, String message) {
        super(eventId);
        this.videoId = videoId;
        this.message = message;
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
