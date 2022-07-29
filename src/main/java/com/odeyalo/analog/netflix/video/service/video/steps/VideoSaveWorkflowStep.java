package com.odeyalo.analog.netflix.video.service.video.steps;

import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.exceptions.WorkflowStepException;
import com.odeyalo.analog.netflix.video.service.video.dto.UploadVideoInformation;

public interface VideoSaveWorkflowStep {
    /**
     *
     * @param information - to get information about video
     * @param rawVideo - to build video entity
     */
    void process(UploadVideoInformation information, Video rawVideo) throws WorkflowStepException;

}
