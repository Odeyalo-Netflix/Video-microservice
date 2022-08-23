package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface VideoManager {

    void saveVideo(Video video);

    void uploadVideo(MultipartFile file, UploadVideoData data) throws VideoUploadException;

    void deleteVideo(String id);

    void updateVideo(Video old, Video newVideo);

    List<Video> getVideosByUser(String userId);

    Video getVideo(String id);

}
