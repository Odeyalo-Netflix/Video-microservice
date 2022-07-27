package com.odeyalo.analog.netflix.video.service.video;

import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.service.video.dto.UploadVideoInformation;
import com.odeyalo.analog.netflix.video.service.video.steps.VideoSaveWorkflowStep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
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
        this.logger.info("Starting video saga process with information: {}", information);
        this.steps.forEach(step -> step.process(information, video));
        this.logger.info("Saga successful finished");
    }
}
