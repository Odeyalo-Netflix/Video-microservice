package com.odeyalo.analog.netflix.video.service.events.handler;

import com.odeyalo.analog.netflix.video.dto.SearchVideoEntity;
import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.exceptions.WrongEventParameterException;
import com.odeyalo.analog.netflix.video.service.events.Event;
import com.odeyalo.analog.netflix.video.service.events.EventHandlerAutoRegistry;
import com.odeyalo.analog.netflix.video.service.events.VideoEntitySuccessSavedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * Registry video entity in SEARCH_MICROSERVICE using KAFKA
 */
@Component
public class SearchRegistryOnVideoEntitySuccessSavedEventHandler implements EventHandler, EventHandlerAutoRegistry {
    private static final String EVENT_KEY = "VIDEO_ENTITY_SAVED_EVENT";
    private static final String SEARCH_REGISTRY_TOPIC = "SEARCH_REGISTRY_TOPIC";

    private final KafkaTemplate<String, SearchVideoEntity> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(SearchRegistryOnVideoEntitySuccessSavedEventHandler.class);

    @Autowired
    public SearchRegistryOnVideoEntitySuccessSavedEventHandler(KafkaTemplate<String, SearchVideoEntity> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void processEvent(Event e) {
        if (!(e instanceof VideoEntitySuccessSavedEvent)) {
            throw new WrongEventParameterException(String.format("Wrong event presented in event parameters! Expected: %s, presented: %s", VideoEntitySuccessSavedEvent.class.getName(), e.getClass()));
        }
        VideoEntitySuccessSavedEvent event = (VideoEntitySuccessSavedEvent) e;
        this.logger.info("Received event: {}", event);
        Video video = event.getVideo();
        SearchVideoEntity searchEntity = new SearchVideoEntity(video);
        this.kafkaTemplate.send(SEARCH_REGISTRY_TOPIC, searchEntity);
    }

    @Override
    public String getEventKey() {
        return EVENT_KEY;
    }

    @Override
    public EventHandler getEventHandler() {
        return this;
    }
}
