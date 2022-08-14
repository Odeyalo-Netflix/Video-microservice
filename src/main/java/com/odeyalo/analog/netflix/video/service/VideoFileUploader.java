package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.support.clients.filestorage.VideoControllerClient;
import com.odeyalo.support.clients.filestorage.dto.SuccessUploadVideoResponseDTO;
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
        this.logger.info("Video with id: {} was successful uploaded.", Objects.requireNonNull(body).getVideoId());
        return body;
    }
    SuccessUploadVideoResponseDTO send(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body
                = new LinkedMultiValueMap<>();
        body.add("video", file);
        HttpEntity<MultiValueMap<String, Object>> requestEntity
                = new HttpEntity<>(body, headers);
        String serverUrl = "http://localhost:8090/api/v1/video/upload";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<SuccessUploadVideoResponseDTO> response = restTemplate
                .postForEntity(serverUrl, requestEntity, SuccessUploadVideoResponseDTO.class);
        this.logger.info("RESPONSE: {}", response);
        return response.getBody();
    }
}
