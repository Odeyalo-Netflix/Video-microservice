package com.odeyalo.analog.netflix.video.service.video.steps;

public enum VideoSaveWorkflowStepStatus {
    /**
     * This step means that video uploading in the process
     */
    VIDEO_FILE_SAVING_PROCESS_STATUS,
    /**
     * This step means that video was uploaded successful
     */
    VIDEO_FILE_SAVED_SUCCESSFUL_STATUS,

    /**
     * This step means that video entity is saving to DB or another storage
     */
    VIDEO_ENTITY_SAVING_STATUS
}
