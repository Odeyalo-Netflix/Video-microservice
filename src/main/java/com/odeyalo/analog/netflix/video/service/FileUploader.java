package com.odeyalo.analog.netflix.video.service;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploader<R> {
    /**
     *
     * @param file - file to upload
     * @return - return generic type
     */
    R uploadFile(MultipartFile file);
}
