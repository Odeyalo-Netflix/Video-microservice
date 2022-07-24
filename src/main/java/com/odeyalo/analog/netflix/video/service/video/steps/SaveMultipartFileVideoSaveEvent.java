package com.odeyalo.analog.netflix.video.service.video.steps;

import org.springframework.web.multipart.MultipartFile;

public class SaveMultipartFileVideoSaveEvent extends VideoSaveEvent {
    protected MultipartFile videoFile;
}
