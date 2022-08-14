package com.odeyalo.analog.netflix.video.service.events;

public class VideoUploadedSuccessEvent extends Event {
    protected String videoId;
    protected String fileId;

    public VideoUploadedSuccessEvent() {
    }

    public VideoUploadedSuccessEvent(String eventId, String videoId, String fileId) {
        super(eventId);
        this.videoId = videoId;
        this.fileId = fileId;
    }

    public String getVideoId() {
        return videoId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
