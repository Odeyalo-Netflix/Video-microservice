package com.odeyalo.analog.netflix.video.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Video {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private VideoType videoType;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String streamUrl;
    @Column(nullable = false)
    private String poster;
    @Column(nullable = false)
    private LocalDate year;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public static VideoBuilder getBuilder() {
        return new VideoBuilder();
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id) && Objects.equals(name, video.name) && videoType == video.videoType && Objects.equals(description, video.description) && Objects.equals(streamUrl, video.streamUrl) && Objects.equals(poster, video.poster) && Objects.equals(year, video.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, videoType, description, streamUrl, poster, year);
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", videoType=" + videoType +
                ", description='" + description + '\'' +
                ", streamUrl='" + streamUrl + '\'' +
                ", poster='" + poster + '\'' +
                ", year=" + year +
                '}';
    }

    public static final class VideoBuilder {
        private String id;
        private String name;
        private VideoType videoType;
        private String description;
        private String streamUrl;
        private String poster;
        private LocalDate year;

        private VideoBuilder() {
        }

        public static VideoBuilder aVideo() {
            return new VideoBuilder();
        }

        public VideoBuilder id(String id) {
            this.id = id;
            return this;
        }

        public VideoBuilder name(String name) {
            this.name = name;
            return this;
        }

        public VideoBuilder videoType(VideoType videoType) {
            this.videoType = videoType;
            return this;
        }

        public VideoBuilder description(String description) {
            this.description = description;
            return this;
        }

        public VideoBuilder streamUrl(String streamUrl) {
            this.streamUrl = streamUrl;
            return this;
        }

        public VideoBuilder poster(String poster) {
            this.poster = poster;
            return this;
        }

        public VideoBuilder year(LocalDate year) {
            this.year = year;
            return this;
        }

        public Video build() {
            if (id == null)
                id = UUID.randomUUID().toString(); // auto id generation
            Video video = new Video();
            video.setId(id);
            video.setName(name);
            video.setVideoType(videoType);
            video.setDescription(description);
            video.poster = this.poster;
            video.streamUrl = this.streamUrl;
            video.year = this.year;
            return video;
        }
    }
}
