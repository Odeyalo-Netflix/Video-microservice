package com.odeyalo.analog.netflix.video.service.video.steps;

import java.util.List;

public interface VideoSaveWorkflow {
    /**
     *
     * @return - steps that needed to save video
     */
    List<VideoSaveWorkflowStep> getSteps();
}
