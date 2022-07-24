package com.odeyalo.analog.netflix.video.dto;

import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.entity.VideoType;

import java.time.LocalDate;

public class UploadVideoData extends GenericVideoData {
    private VideoType videoType;
    private String description;
    private String poster;
    private LocalDate year;

    public UploadVideoData(String videoName, VideoType videoType,
                           String description, String poster,
                           LocalDate year) {
        super(videoName);
        this.videoType = videoType;
        this.description = description;
        this.poster = poster;
        this.year = year;
    }

    public static UploadVideoData toUploadVideoData(Video video) {
        return new UploadVideoData(video.getName(), video.getVideoType(), video.getDescription(), video.getPoster(), video.getYear());
    }

    public static UploadVideoData toUploadVideoData(UploadVideoDTO dto) {
        return new UploadVideoData(dto.getName(), dto.getVideoType(), dto.getDescription(), dto.getPoster(), dto.getYear());
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }
}
