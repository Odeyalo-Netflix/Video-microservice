package com.odeyalo.analog.netflix.video.service.video.steps;

import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.service.FileUploader;
import com.odeyalo.analog.netflix.video.service.video.dto.UploadVideoInformation;
import com.odeyalo.support.clients.filestorage.dto.ImageSuccessfulUploadedResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Order(value = 2)
public class UploadPosterFileVideoSaveWorkflowStep implements VideoSaveWorkflowStep {
    private final FileUploader<ImageSuccessfulUploadedResponseDTO> imageFileUploader;
    private final Logger logger = LoggerFactory.getLogger(UploadPosterFileVideoSaveWorkflowStep.class);

    @Autowired
    public UploadPosterFileVideoSaveWorkflowStep(FileUploader<ImageSuccessfulUploadedResponseDTO> imageFileUploader) {
        this.imageFileUploader = imageFileUploader;
    }

    @Override
    public void process(UploadVideoInformation information, Video rawVideo) {
        this.logger.info("Starting poster file uploading process. Video information {}, raw video: {}", information, rawVideo);
        MultipartFile posterFile = information.getPosterFile();
        ImageSuccessfulUploadedResponseDTO dto = this.imageFileUploader.uploadFile(posterFile);
        String posterFileId = dto.getImageId();
        rawVideo.setPosterFileId(posterFileId);
    }

    @Override
    public void revert(UploadVideoInformation information) {

    }
}
