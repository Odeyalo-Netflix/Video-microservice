package com.odeyalo.analog.netflix.video.service.facade;

import com.odeyalo.analog.netflix.video.dto.UploadVideoDTO;
import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.exceptions.PosterUploadException;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import com.odeyalo.analog.netflix.video.service.video.AsyncVideoSagaOrchestrationManager;
import com.odeyalo.analog.netflix.video.service.video.dto.UploadVideoInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoUploadServiceFacadeImpl implements VideoUploadServiceFacade {
    private final AsyncVideoSagaOrchestrationManager manager;
    private final Logger logger = LoggerFactory.getLogger(VideoUploadServiceFacadeImpl.class);

    @Autowired
    public VideoUploadServiceFacadeImpl(AsyncVideoSagaOrchestrationManager manager) {
        this.manager = manager;
    }

    @Override
    public void uploadVideo(UploadVideoDTO dto, String userId, MultipartFile video, MultipartFile poster) {
        UploadVideoData data = UploadVideoData.toUploadVideoData(dto, userId);
        UploadVideoInformation uploadVideoInformation = new UploadVideoInformation(data, video, poster);
        this.logger.info("Starting saga with information: {}", uploadVideoInformation);
        this.manager.processSagaTransaction(uploadVideoInformation);
    }
}
