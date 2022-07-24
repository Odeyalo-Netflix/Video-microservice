package com.odeyalo.analog.netflix.video.dto;

import com.odeyalo.analog.netflix.video.entity.Video;

public class StorageVideoData extends GenericVideoData {
    protected String videoId;

    public StorageVideoData(String videoId, String videoName) {
        super(videoName);
        this.videoId = videoId;
    }

    public static StorageVideoData toStorageVideoData(Video video) {
        return new StorageVideoData(video.getId(), video.getName());
    }

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
