package com.odeyalo.analog.netflix.video.service.video.steps.dto;

public class VideoSaveWorkflowMessage {
    private String videoName;
    private String description;

    public VideoSaveWorkflowMessage(String videoName, String description) {
        this.videoName = videoName;
        this.description = description;
    }

    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
