package com.odeyalo.analog.netflix.video.service.video.steps;

import com.odeyalo.analog.netflix.video.repository.VideoRepository;
import com.odeyalo.analog.netflix.video.service.video.steps.dto.SaveEntityVideoSaveWorkflowMessage;
import org.springframework.stereotype.Service;

@Service
public class SaveEntityVideoSaveWorkflowStep implements VideoSaveWorkflowStep<SaveEntityVideoSaveWorkflowMessage> {
    private final VideoRepository repository;

    public SaveEntityVideoSaveWorkflowStep(VideoRepository repository) {
        this.repository = repository;
    }

    @Override
    public VideoSaveWorkflowStepStatus getStatus() {
        return VideoSaveWorkflowStepStatus.VIDEO_ENTITY_SAVING_STATUS;
    }

    @Override
    public void process(SaveEntityVideoSaveWorkflowMessage message) {
        //todo maybe add redis to application and get values using unique id
    }

    @Override
    public void revert(SaveEntityVideoSaveWorkflowMessage message) {

    }
}
