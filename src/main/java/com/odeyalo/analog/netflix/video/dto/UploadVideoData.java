package com.odeyalo.analog.netflix.video.dto;

import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.entity.VideoType;

import java.time.LocalDate;

public class UploadVideoData extends GenericVideoData {
    private VideoType videoType;
    private String description;
    private LocalDate year;
    private String userId;

    public UploadVideoData(String videoName, VideoType videoType,
                           String description,
                           LocalDate year, String userId) {
        super(videoName);
        this.videoType = videoType;
        this.description = description;
        this.year = year;
        this.userId = userId;
    }

    public static UploadVideoData toUploadVideoData(Video video) {
        return new UploadVideoData(video.getName(), video.getVideoType(), video.getDescription(), video.getYear(), video.getUserId());
    }

    public static UploadVideoData toUploadVideoData(UploadVideoDTO dto, String userId) {
        return new UploadVideoData(dto.getName(), dto.getVideoType(), dto.getDescription(), dto.getYear(), userId);
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

    public LocalDate getYear() {
        return year;
    }

    public void setYear(LocalDate year) {
        this.year = year;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UploadVideoData{" +
                "videoType=" + videoType +
                ", description='" + description + '\'' +
                ", year=" + year +
                '}';
    }
}
