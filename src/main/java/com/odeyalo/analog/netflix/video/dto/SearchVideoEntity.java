package com.odeyalo.analog.netflix.video.dto;

import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.entity.VideoType;

import java.time.LocalDate;

public class SearchVideoEntity {
    private final String id;
    private final String name;
    private final VideoType videoType;
    private final String description;
    private final String videoFileId;
    private final String posterFileId;
    private final LocalDate year;

    public SearchVideoEntity(Video video) {
        this(video.getId(), video.getName(), video.getVideoType(), video.getDescription(), video.getVideoFileId(), video.getPosterFileId(), video.getYear());
    }

    private SearchVideoEntity(String id, String name, VideoType videoType, String description, String videoFileId, String posterFileId, LocalDate year) {
        this.id = id;
        this.name = name;
        this.videoType = videoType;
        this.description = description;
        this.videoFileId = videoFileId;
        this.posterFileId = posterFileId;
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public VideoType getVideoType() {
        return videoType;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoFileId() {
        return videoFileId;
    }

    public String getPosterFileId() {
        return posterFileId;
    }

    public LocalDate getYear() {
        return year;
    }

    public static SearchVideoEntityBuilder fromId(String id) {
        return new SearchVideoEntityBuilder(id);
    }

    public static final class SearchVideoEntityBuilder {
        private String id;
        private String name;
        private VideoType videoType;
        private String description;
        private String videoFileId;
        private String posterFileId;
        private LocalDate year;

        private SearchVideoEntityBuilder() {
        }

        private SearchVideoEntityBuilder(String id) {
            this.id = id;
        }

        public SearchVideoEntityBuilder id(String id) {
            this.id = id;
            return this;
        }

        public SearchVideoEntityBuilder name(String name) {
            this.name = name;
            return this;
        }

        public SearchVideoEntityBuilder videoType(VideoType videoType) {
            this.videoType = videoType;
            return this;
        }

        public SearchVideoEntityBuilder description(String description) {
            this.description = description;
            return this;
        }

        public SearchVideoEntityBuilder videoFileId(String videoFileId) {
            this.videoFileId = videoFileId;
            return this;
        }

        public SearchVideoEntityBuilder posterFileId(String posterFileId) {
            this.posterFileId = posterFileId;
            return this;
        }

        public SearchVideoEntityBuilder year(LocalDate year) {
            this.year = year;
            return this;
        }

        public SearchVideoEntity build() {
            return new SearchVideoEntity(id, name, videoType, description, videoFileId, posterFileId, year);
        }
    }
}
