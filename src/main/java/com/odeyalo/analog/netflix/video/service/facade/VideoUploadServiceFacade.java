package com.odeyalo.analog.netflix.video.service.facade;

import com.odeyalo.analog.netflix.video.dto.UploadVideoDTO;
import com.odeyalo.analog.netflix.video.exceptions.PosterUploadException;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import org.springframework.web.multipart.MultipartFile;

public interface VideoUploadServiceFacade {
    /**
     *
     * @param dto - info about video
     * @param video - file with video
     * @param poster - image to video
     */
    void uploadVideo(UploadVideoDTO dto, MultipartFile video, MultipartFile poster) throws PosterUploadException, VideoUploadException;
}
