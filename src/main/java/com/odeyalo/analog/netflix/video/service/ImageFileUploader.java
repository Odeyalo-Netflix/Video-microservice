package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.support.clients.filestorage.ImageControllerClient;
import com.odeyalo.support.clients.filestorage.dto.SuccessUploadImageResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class ImageFileUploader implements FileUploader<SuccessUploadImageResponseDTO> {
    private final ImageControllerClient client;
    private final Logger logger = LoggerFactory.getLogger(ImageFileUploader.class);
    @Autowired
    RestTemplate restTemplate;
    public ImageFileUploader(ImageControllerClient client) {
        this.client = client;
    }


    @Override
    public SuccessUploadImageResponseDTO uploadFile(MultipartFile file) {
        ResponseEntity<SuccessUploadImageResponseDTO> response = this.client.saveImage(file);
        SuccessUploadImageResponseDTO body = response.getBody();
        this.logger.info("Success uploaded image. Image id: {}.", Objects.requireNonNull(body).getId());
        return body;
    }

    SuccessUploadImageResponseDTO send(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("image", file);
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        String serverUrl = "http://localhost:8090/api/v1/image/upload";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SuccessUploadImageResponseDTO> response = restTemplate
                .postForEntity(serverUrl, requestEntity, SuccessUploadImageResponseDTO.class);
        this.logger.info("RESPONSE: {}", response);
        return response.getBody();
    }
}
