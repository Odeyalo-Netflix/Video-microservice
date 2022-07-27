package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.support.clients.filestorage.ImageControllerClient;
import com.odeyalo.support.clients.filestorage.dto.SuccessUploadImageResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class ImageFileUploader implements FileUploader<SuccessUploadImageResponseDTO> {
    private final ImageControllerClient client;
    private final Logger logger = LoggerFactory.getLogger(ImageFileUploader.class);

    public ImageFileUploader(ImageControllerClient client) {
        this.client = client;
    }


    @Override
    public SuccessUploadImageResponseDTO uploadFile(MultipartFile file) {
        ResponseEntity<SuccessUploadImageResponseDTO> response = this.client.saveImage(file);
        SuccessUploadImageResponseDTO body = response.getBody();
        this.logger.info("Success uploaded image. Image id: {}. Status: {}", Objects.requireNonNull(body).getId(), response.getStatusCode());
        return body;
    }
}
