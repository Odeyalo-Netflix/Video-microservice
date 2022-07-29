package com.odeyalo.analog.netflix.video.service.video;

import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.exceptions.WorkflowStepException;
import com.odeyalo.analog.netflix.video.service.utils.MultipartFileUtils;
import com.odeyalo.analog.netflix.video.service.video.dto.UploadVideoInformation;
import com.odeyalo.analog.netflix.video.service.video.steps.VideoSaveWorkflowStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

@Service
public class AsyncVideoSagaOrchestrationManager implements SagaOrchestrationManager<UploadVideoInformation> {

    private final List<VideoSaveWorkflowStep> steps;
    private final Logger logger = LoggerFactory.getLogger(AsyncVideoSagaOrchestrationManager.class);
    @Autowired
    public AsyncVideoSagaOrchestrationManager(List<VideoSaveWorkflowStep> steps) {
        this.steps = steps;
    }

    @PostConstruct
    void logMessage() {
        this.logger.info("Will process saga process in this order: {}", steps);
    }

    @Override
    public void processSagaTransaction(UploadVideoInformation information) {
        Video video = Video.builder().build();
        try {
            MultipartFile videoFile = MultipartFileUtils.copyMultipartFile(information.getVideoFile());
            MultipartFile posterFile = MultipartFileUtils.copyMultipartFile(information.getPosterFile());
            UploadVideoData data = information.getData();
            UploadVideoInformation copiedInfo = new UploadVideoInformation(data, videoFile, posterFile);
            Thread thread = new Thread(() -> {
                for (VideoSaveWorkflowStep step : steps) {
                    step.process(copiedInfo, video);
                }
            });
            thread.setName("VIDEO_UPLOADER_THREAD_1");
            thread.start();
        } catch (IOException ex) {
            this.logger.error("Error during file copying.", ex);
            throw new WorkflowStepException("Cannot process video to server!");
        }
    }
}
