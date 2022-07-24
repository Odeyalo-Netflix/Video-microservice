package com.odeyalo.analog.netflix.video.service.storage;

import com.odeyalo.analog.netflix.video.dto.StorageVideoData;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import com.odeyalo.support.clients.filestorage.VideoControllerClient;
import com.odeyalo.support.clients.filestorage.dto.SuccessUploadVideoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Component
public class AsyncHttpMicroserviceDelegateRemoteVideoStorage implements RemoteVideoStorage {
    private final Logger logger = LoggerFactory.getLogger(AsyncHttpMicroserviceDelegateRemoteVideoStorage.class);
    private final VideoControllerClient client;

    @Autowired
    public AsyncHttpMicroserviceDelegateRemoteVideoStorage(VideoControllerClient client) {
        this.client = client;
    }

    @Async
    @Override
    public void storeVideo(MultipartFile video, StorageVideoData data) {
        ResponseEntity<SuccessUploadVideoResponseDTO> response = this.client.saveVideo(video);
        String videoId = Objects.requireNonNull(response.getBody()).getVideoId();
        this.logger.info("Successful saved video to other microservice, video id: {}", videoId);
    }
}
