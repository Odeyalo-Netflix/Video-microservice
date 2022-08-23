package com.odeyalo.analog.netflix.video.service.video;

import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.exceptions.WorkflowStepException;
import com.odeyalo.analog.netflix.video.service.events.EventHandlerManager;
import com.odeyalo.analog.netflix.video.service.events.VideoEntitySuccessSavedEvent;
import com.odeyalo.analog.netflix.video.service.utils.MultipartFileUtils;
import com.odeyalo.analog.netflix.video.service.video.dto.UploadVideoInformation;
import com.odeyalo.analog.netflix.video.service.video.steps.VideoSaveWorkflowStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class AsyncVideoSagaOrchestrationManager implements SagaOrchestrationManager<UploadVideoInformation> {
    private final EventHandlerManager eventHandlerManager;
    private final List<VideoSaveWorkflowStep> steps;
    private final Logger logger = LoggerFactory.getLogger(AsyncVideoSagaOrchestrationManager.class);
    private static final String VIDEO_UPLOADED_EVENT_KEY = "VIDEO_ENTITY_SAVED_EVENT";

    @Autowired
    public AsyncVideoSagaOrchestrationManager(List<VideoSaveWorkflowStep> steps, @Qualifier("asyncEventHandlerManager") EventHandlerManager eventHandlerManager) {
        this.steps = steps;
        this.eventHandlerManager = eventHandlerManager;
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
                try {
                    for (VideoSaveWorkflowStep step : steps) {
                        step.process(copiedInfo, video);
                    }
                    this.eventHandlerManager.fireEvent(VIDEO_UPLOADED_EVENT_KEY, new VideoEntitySuccessSavedEvent(UUID.randomUUID().toString(), video));
                } catch (Exception e) {
                    this.logger.error("Error during saga process, starting revert process", e);
                }
            });
            thread.setName("VIDEO_UPLOADER_THREAD_1");
            thread.start();
        } catch (IOException ex) {
            this.logger.error("Error during file copy.", ex);
            throw new WorkflowStepException("Cannot process video to server!");
        }
    }

    protected final void revertSagaTransaction(List<VideoSaveWorkflowStep> steps) {
        Collections.reverse(steps);
//        for (VideoSaveWorkflowStep step : steps) {
//            step.revert();
//        }
    }
}
