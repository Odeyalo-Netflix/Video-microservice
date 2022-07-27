package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.support.clients.filestorage.VideoControllerClient;
import com.odeyalo.support.clients.filestorage.dto.SuccessUploadVideoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class VideoFileUploader implements FileUploader<SuccessUploadVideoResponseDTO> {
    private final VideoControllerClient client;
    private final Logger logger = LoggerFactory.getLogger(VideoFileUploader.class);

    @Autowired
    public VideoFileUploader(VideoControllerClient client) {
        this.client = client;
    }

    @Override
    public SuccessUploadVideoResponseDTO uploadFile(MultipartFile file) {
        ResponseEntity<SuccessUploadVideoResponseDTO> response = client.saveVideo(file);
        SuccessUploadVideoResponseDTO body = response.getBody();
        this.logger.info("Video with id: {} was successful uploaded. Response STATUS CODE: {}", Objects.requireNonNull(body).getVideoId(), response.getStatusCode());
        return body;
    }
}
