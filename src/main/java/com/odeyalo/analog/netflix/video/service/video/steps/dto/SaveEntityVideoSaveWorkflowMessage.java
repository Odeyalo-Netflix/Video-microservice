package com.odeyalo.analog.netflix.video.service.video.steps.dto;

public class SaveEntityVideoSaveWorkflowMessage extends VideoSaveWorkflowMessage {
    private String fileId;
    private String posterId;

    public SaveEntityVideoSaveWorkflowMessage(String videoName, String description) {
        super(videoName, description);
    }
}
