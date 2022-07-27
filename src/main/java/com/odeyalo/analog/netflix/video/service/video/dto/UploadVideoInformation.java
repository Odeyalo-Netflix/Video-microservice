package com.odeyalo.analog.netflix.video.service.video.dto;

import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import org.springframework.web.multipart.MultipartFile;

public class UploadVideoInformation {
    private final UploadVideoData data;
    private final MultipartFile videoFile;
    private final MultipartFile posterFile;

    public UploadVideoInformation(UploadVideoData data, MultipartFile videoFile, MultipartFile posterFile) {
        this.data = data;
        this.videoFile = videoFile;
        this.posterFile = posterFile;
    }

    public MultipartFile getVideoFile() {
        return videoFile;
    }

    public MultipartFile getPosterFile() {
        return posterFile;
    }

    public UploadVideoData getData() {
        return data;
    }

    @Override
    public String toString() {
        return "UploadVideoInformation{" +
                "data=" + data +
                ", videoFile=" + videoFile +
                ", posterFile=" + posterFile +
                '}';
    }
}
