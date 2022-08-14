package com.odeyalo.analog.netflix.video.service.video.listeners;

import com.odeyalo.analog.netflix.video.service.events.VideoUploadFailureEvent;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class VideoUploadFailureEventListener extends AbstractEventListener<VideoUploadFailureEvent> {

    @Override
    @KafkaListener(topics = {
            "VIDEO_UPLOAD_FAILURE"
    })
    public void listenEvent(VideoUploadFailureEvent event) {
        String eventId = event.getEventId();
        String videoId = event.getVideoId();
        String message = event.getMessage();
        this.logger.error("Received event that video uploading was failed. Event id: {}, Video id: {}, message: {}", eventId, videoId, message);
    }
}
