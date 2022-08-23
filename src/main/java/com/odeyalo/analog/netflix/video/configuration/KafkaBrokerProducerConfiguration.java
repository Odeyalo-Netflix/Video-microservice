package com.odeyalo.analog.netflix.video.configuration;


import com.odeyalo.analog.netflix.video.dto.SearchVideoEntity;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaBrokerProducerConfiguration {
    private final Logger logger = LoggerFactory.getLogger(KafkaBrokerProducerConfiguration.class);

    @Value("${kafka.connection.url}")
    private String KAFKA_CONNECTION_URL;

    @PostConstruct
    public void init() {
        this.logger.info("Kafka connection url: {}", KAFKA_CONNECTION_URL);
    }

    @Bean
    public KafkaTemplate<String, String> jsonKafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public ProducerFactory<String, String> producerFactory() {
        return new DefaultKafkaProducerFactory<>(config());
    }
    @Bean
    public KafkaTemplate<String, SearchVideoEntity> searchVideoEntityKafkaTemplate() {
        return new KafkaTemplate<>(searchVideoEntityProducerFactory());
    }

    @Bean
    public ProducerFactory<String, SearchVideoEntity> searchVideoEntityProducerFactory() {
        return new DefaultKafkaProducerFactory<>(config());
    }

    @Bean
    public Map<String, Object> config() {
        HashMap<String, Object> config = new HashMap<>(5);
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KAFKA_CONNECTION_URL);
        config.put(ProducerConfig.LINGER_MS_CONFIG, 10);
        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
        return config;
    }
}
