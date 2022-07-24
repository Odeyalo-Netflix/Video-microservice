package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.analog.netflix.video.exceptions.PosterUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface VideoPosterSaverService {
    /**
     *
     * @param image - image to save
     * @return - url or path to image
     */
    String saveImage(MultipartFile image) throws PosterUploadException;

}
