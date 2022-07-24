package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import com.odeyalo.analog.netflix.video.repository.VideoRepository;
import com.odeyalo.analog.netflix.video.service.storage.VideoStorage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DefaultVideoSaverService implements VideoSaverService {
    private final VideoRepository videoRepository;
    private final Logger logger = LoggerFactory.getLogger(DefaultVideoSaverService.class);

    @Autowired
    public DefaultVideoSaverService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    @Override
    public void saveVideo(Video video) {
        this.videoRepository.save(video);
        this.logger.info("Successful saved video: {}", video);
    }
}
