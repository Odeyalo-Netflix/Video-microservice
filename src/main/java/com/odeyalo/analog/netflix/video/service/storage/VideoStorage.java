package com.odeyalo.analog.netflix.video.service.storage;

import com.odeyalo.analog.netflix.video.dto.StorageVideoData;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface VideoStorage {

    /**
     *
     * @param video - file to save
     * @param videoData = generic info about video
     * @return URL or PATH to video for streaming it
     * @throws VideoUploadException
     */
    void storeVideo(MultipartFile video, StorageVideoData videoData) throws VideoUploadException;

}
