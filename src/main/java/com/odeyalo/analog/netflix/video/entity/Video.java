package com.odeyalo.analog.netflix.video.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class Video {
    @Id
    private String id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private VideoType videoType;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String videoFileId;
    @Column(nullable = false)
    private String posterFileId;
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

    public String getVideoFileId() {
        return videoFileId;
    }

    public void setVideoFileId(String streamUrl) {
        this.videoFileId = streamUrl;
    }

    public String getPosterFileId() {
        return posterFileId;
    }

    public void setPosterFileId(String poster) {
        this.posterFileId = poster;
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
        return Objects.equals(id, video.id) && Objects.equals(name, video.name) && videoType == video.videoType && Objects.equals(description, video.description) && Objects.equals(videoFileId, video.videoFileId) && Objects.equals(posterFileId, video.posterFileId) && Objects.equals(year, video.year);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, videoType, description, videoFileId, posterFileId, year);
    }

    @Override
    public String toString() {
        return "Video{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", videoType=" + videoType +
                ", description='" + description + '\'' +
                ", streamUrl='" + videoFileId + '\'' +
                ", poster='" + posterFileId + '\'' +
                ", year=" + year +
                '}';
    }

    public static VideoBuilder builder() {
        return new VideoBuilder();
    }

    public static VideoBuilder from(Video video) {
        return new VideoBuilder(video);
    }

    /**
     * Copy video values to this object
     * @param otherVideo - video that contains original values
     */
    public void copy(Video otherVideo) {
        this.id = otherVideo.getId();
        this.name = otherVideo.getName();
        this.description = otherVideo.getDescription();
        this.videoFileId = otherVideo.getVideoFileId();
        this.posterFileId = otherVideo.getPosterFileId();
        this.videoType = otherVideo.getVideoType();
        this.year = otherVideo.getYear();
    }

    public static final class VideoBuilder {
        private String id;
        private String name;
        private VideoType videoType;
        private String description;
        private String videoFileId;
        private String posterFileId;
        private LocalDate year;

        private VideoBuilder() {
        }

        private VideoBuilder(Video video) {
            this.videoType = video.getVideoType();
            this.videoFileId = video.getVideoFileId();
            this.description = video.getDescription();
            this.posterFileId = video.getPosterFileId();
            this.name = video.getName();
            this.id = video.getId();
            this.year = video.getYear();
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

        public VideoBuilder videoFileId(String videoFileId) {
            this.videoFileId = videoFileId;
            return this;
        }

        public VideoBuilder posterFileId(String posterFileId) {
            this.posterFileId = posterFileId;
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
            video.posterFileId = this.posterFileId;
            video.videoFileId = this.videoFileId;
            video.year = this.year;
            return video;
        }
    }
}
