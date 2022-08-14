package com.odeyalo.analog.netflix.video.service;

import com.odeyalo.analog.netflix.video.entity.Video;

public interface VideoSaverService {
    /**
     *
     * @param video - video entity to save
     */
    Video saveVideo(Video video);

}
