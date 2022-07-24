package com.odeyalo.analog.netflix.video.dto;

import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.entity.VideoType;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class VideoInformationResponseDTO {
    private String name;
    private VideoType videoType;
    private String description;
    private String streamUrl;
    private String poster;
    private LocalDate year;

    public static VideoInformationResponseDTO toVideoInformationResponse(Video video) {
        return new VideoInformationResponseDTO(video.getName(), video.getVideoType(), video.getDescription(), video.getStreamUrl(), video.getPoster(), video.getYear());
    }

    public VideoInformationResponseDTO(String name, VideoType videoType, String description, String streamUrl, String poster, LocalDate year) {
        this.name = name;
        this.videoType = videoType;
        this.description = description;
        this.streamUrl = streamUrl;
        this.poster = poster;
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStreamUrl() {
        return streamUrl;
    }

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
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
        return "VideoInformationResponseDTO{" +
                "name='" + name + '\'' +
                ", videoType=" + videoType +
                ", description='" + description + '\'' +
                ", streamUrl='" + streamUrl + '\'' +
                ", poster='" + poster + '\'' +
                ", year=" + year +
                '}';
    }

    public static VideoInformationResponseDTOBuilder builder() {
        return new VideoInformationResponseDTOBuilder();
    }
    public static final class VideoInformationResponseDTOBuilder {
        private String name;
        private VideoType videoType;
        private String description;
        private String streamUrl;
        private String poster;
        private LocalDate year;

        private VideoInformationResponseDTOBuilder() {}

        public VideoInformationResponseDTOBuilder name(String name) {
            this.name = name;
            return this;
        }

        public VideoInformationResponseDTOBuilder videoType(VideoType videoType) {
            this.videoType = videoType;
            return this;
        }

        public VideoInformationResponseDTOBuilder description(String description) {
            this.description = description;
            return this;
        }

        public VideoInformationResponseDTOBuilder streamUrl(String streamUrl) {
            this.streamUrl = streamUrl;
            return this;
        }

        public VideoInformationResponseDTOBuilder poster(String poster) {
            this.poster = poster;
            return this;
        }

        public VideoInformationResponseDTOBuilder year(LocalDate year) {
            this.year = year;
            return this;
        }

        public VideoInformationResponseDTO build() {
            return new VideoInformationResponseDTO(name, videoType, description, streamUrl, poster, year);
        }
    }
}
