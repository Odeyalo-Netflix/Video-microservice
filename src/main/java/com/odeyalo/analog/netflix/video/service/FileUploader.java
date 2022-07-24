package com.odeyalo.analog.netflix.video.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader<R> {

    R uploadFile(MultipartFile file);
}
