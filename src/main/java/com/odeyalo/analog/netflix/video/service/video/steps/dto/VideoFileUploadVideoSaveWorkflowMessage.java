package com.odeyalo.analog.netflix.video.service.video.steps.dto;

import org.springframework.web.multipart.MultipartFile;

public class VideoFileUploadVideoSaveWorkflowMessage extends VideoSaveWorkflowMessage {
    private final MultipartFile videoFile;

    public VideoFileUploadVideoSaveWorkflowMessage(String videoName, String description, MultipartFile videoFile) {
        super(videoName, description);
        this.videoFile = videoFile;
    }


    public MultipartFile getVideoFile() {
        return videoFile;
    }
}
