package com.odeyalo.analog.netflix.video.service.video.steps;

import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.service.video.steps.dto.VideoSaveWorkflowMessage;

public interface VideoSaveWorkflowStep<T extends VideoSaveWorkflowMessage> {
    /**
     *
     * @return - current status of the transaction
     */
    VideoSaveWorkflowStepStatus getStatus();

    /**
     * Process this step
     */
    void process(T message);


    /**
     * Revert transaction, if failed
     */
    void revert(T message);
}
