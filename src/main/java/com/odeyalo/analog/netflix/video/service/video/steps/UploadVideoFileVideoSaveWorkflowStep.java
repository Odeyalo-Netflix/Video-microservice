package com.odeyalo.analog.netflix.video.service.video.steps;

import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.service.FileUploader;
import com.odeyalo.analog.netflix.video.service.video.dto.UploadVideoInformation;
import com.odeyalo.support.clients.filestorage.dto.SuccessUploadVideoResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Order(value = 1)
public class UploadVideoFileVideoSaveWorkflowStep implements VideoSaveWorkflowStep {
    private final FileUploader<SuccessUploadVideoResponseDTO> fileUploader;
    private final Logger logger = LoggerFactory.getLogger(UploadVideoFileVideoSaveWorkflowStep.class);

    @Autowired
    public UploadVideoFileVideoSaveWorkflowStep(@Qualifier("videoFileUploader") FileUploader<SuccessUploadVideoResponseDTO> fileUploader) {
        this.fileUploader = fileUploader;
    }

    @Override
    public void process(UploadVideoInformation information, Video video) {
        this.logger.info("Starting video file uploading process. Video information {}, raw video: {}", information, video);
        MultipartFile videoFile = information.getVideoFile();
        SuccessUploadVideoResponseDTO dto = this.fileUploader.uploadFile(videoFile);
        String videoId = dto.getVideoId();
        video.setVideoFileId(videoId);
        this.logger.info("Successful saved video file: {}", videoId);
    }
}
