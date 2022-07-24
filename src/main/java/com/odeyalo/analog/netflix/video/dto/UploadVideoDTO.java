package com.odeyalo.analog.netflix.video.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.odeyalo.analog.netflix.video.entity.VideoType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class UploadVideoDTO {
    private String name;
    private String description;
    private VideoType videoType;
    private String poster;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
    private LocalDate year;

    public UploadVideoDTO() {}

    public UploadVideoDTO(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public void setVideoType(VideoType videoType) {
        this.videoType = videoType;
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

    @Override
    public String toString() {
        return "UploadVideoDTO{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", videoType=" + videoType +
                ", poster='" + poster + '\'' +
                ", year=" + year +
                '}';
    }
}
