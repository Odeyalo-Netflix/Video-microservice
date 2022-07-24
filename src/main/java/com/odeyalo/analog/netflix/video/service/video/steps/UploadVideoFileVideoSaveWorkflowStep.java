package com.odeyalo.analog.netflix.video.service.video.steps;

import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.service.FileUploader;
import com.odeyalo.analog.netflix.video.service.video.steps.dto.VideoFileUploadVideoSaveWorkflowMessage;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@Service
public class UploadVideoFileVideoSaveWorkflowStep implements VideoSaveWorkflowStep<VideoFileUploadVideoSaveWorkflowMessage> {
    private final FileUploader<?> fileUploader;

    public UploadVideoFileVideoSaveWorkflowStep(@Qualifier("asyncVideoFileUploader") FileUploader<?> fileUploader) {
        this.fileUploader = fileUploader;
    }

    @Override
    public VideoSaveWorkflowStepStatus getStatus() {
        return VideoSaveWorkflowStepStatus.VIDEO_FILE_SAVING_PROCESS_STATUS;
    }

    @Override
    public void process(VideoFileUploadVideoSaveWorkflowMessage data) {
        MultipartFile videoFile = data.getVideoFile();
        this.fileUploader.uploadFile(videoFile);
    }

    @Override
    public void revert(VideoFileUploadVideoSaveWorkflowMessage data) {

    }
}
