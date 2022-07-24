package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.support.clients.filestorage.VideoControllerClient;
import com.odeyalo.support.clients.filestorage.dto.SuccessUploadVideoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class AsyncVideoFileUploader implements FileUploader<CompletableFuture<Void>> {
    private final VideoControllerClient client;
    private final Logger logger = LoggerFactory.getLogger(AsyncVideoFileUploader.class);

    @Autowired
    public AsyncVideoFileUploader(VideoControllerClient client) {
        this.client = client;
    }

    @Override
    @Async
    public CompletableFuture<Void> uploadFile(MultipartFile file) {
        ResponseEntity<SuccessUploadVideoResponseDTO> response = client.saveVideo(file);
        this.logger.info("Video with id: {} was successful uploaded. Response STATUS CODE: {}", Objects.requireNonNull(response.getBody()).getVideoId(), response.getStatusCode());
        return CompletableFuture.completedFuture(null);
    }
}
