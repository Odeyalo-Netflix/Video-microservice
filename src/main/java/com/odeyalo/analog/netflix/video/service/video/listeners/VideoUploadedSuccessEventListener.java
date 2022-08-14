package com.odeyalo.analog.netflix.video.service.video.listeners;

import com.odeyalo.analog.netflix.video.service.events.VideoUploadedSuccessEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class VideoUploadedSuccessEventListener extends AbstractEventListener<VideoUploadedSuccessEvent> {

    @Override
    @KafkaListener(topics = {
            "VIDEO_UPLOADED_SUCCESS"
    })
    public void listenEvent(VideoUploadedSuccessEvent event) {
        String videoId = event.getVideoId();
        String fileId = event.getFileId();
        String eventId = event.getEventId();
        this.logger.info("Received event that video was success uploaded. Video id: {}, file id: {}, event id: {}", videoId, fileId, eventId);
    }
}
