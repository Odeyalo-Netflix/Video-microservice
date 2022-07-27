package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.exceptions.VideoNotFoundException;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import com.odeyalo.analog.netflix.video.repository.VideoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class SimpleVideoManager implements VideoManager {
    private final VideoRepository videoRepository;
    private final Logger logger = LoggerFactory.getLogger(SimpleVideoManager.class);
    private final VideoSaverService videoSaverService;
    private final VideoFileUploader uploader;

    public SimpleVideoManager(VideoRepository videoRepository, VideoSaverService videoSaverService, VideoFileUploader uploader) {
        this.videoRepository = videoRepository;
        this.videoSaverService = videoSaverService;
        this.uploader = uploader;
    }

    @Override
    public void saveVideo(Video video) {
        this.videoRepository.save(video);
    }

    @Override
    public void uploadVideo(MultipartFile file, UploadVideoData data) throws VideoUploadException {
        this.uploader.uploadFile(file);
//        this.videoSaverService.saveVideo(data, file);
    }

    @Override
    public void deleteVideo(String id) {

    }

    @Override
    public void updateVideo(Video old, Video newVideo) {

    }

    @Override
    public List<Video> getVideos() {
        return null;
    }

    @Override
    public Video getVideo(String id) {
        Video video = this.videoRepository.findById(id)
                .orElseThrow(() -> new VideoNotFoundException(String.format("Video with id: %s  not found", id)));
        this.logger.info("Founded video: {}", video);
        return video;
    }
}
