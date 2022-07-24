package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.support.clients.filestorage.ImageControllerClient;
import com.odeyalo.support.clients.filestorage.dto.SuccessUploadImageResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
public class HttpMicroserviceDelegateRemoteVideoPosterSaverService implements RemoteVideoPosterSaverService {
    private final ImageControllerClient imageControllerClient;

    @Autowired
    public HttpMicroserviceDelegateRemoteVideoPosterSaverService(ImageControllerClient imageControllerClient) {
        this.imageControllerClient = imageControllerClient;
    }

    @Override
    public String saveImage(MultipartFile image) {
        ResponseEntity<SuccessUploadImageResponseDTO> responseEntity = this.imageControllerClient.saveImage(image); //todo
        return Objects.requireNonNull(responseEntity.getBody()).getId();
    }
}
