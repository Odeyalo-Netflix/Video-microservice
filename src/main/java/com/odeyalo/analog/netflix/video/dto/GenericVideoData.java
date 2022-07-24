package com.odeyalo.analog.netflix.video.dto;

import com.odeyalo.analog.netflix.video.entity.Video;

public class GenericVideoData {
    protected String videoName;

    public GenericVideoData(String videoName) {
        this.videoName = videoName;
    }

    public static GenericVideoData toGenericVideoData(Video video) {
        return new GenericVideoData(video.getName());
    }



    public String getVideoName() {
        return videoName;
    }

    public void setVideoName(String videoName) {
        this.videoName = videoName;
    }

    @Override
    public String toString() {
        return "GenericVideoData{" +
                ", videoName='" + videoName + '\'' +
                '}';
    }
}
