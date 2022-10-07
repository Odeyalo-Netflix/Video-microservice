package com.odeyalo.analog.netflix.video.service.support;

import com.odeyalo.analog.netflix.video.dto.VideoInformationResponseDTO;
import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.exceptions.ImageNotFoundException;
import com.odeyalo.analog.netflix.video.exceptions.VideoNotFoundException;
import com.odeyalo.support.clients.filestorage.ImageControllerClient;
import com.odeyalo.support.clients.filestorage.VideoControllerClient;
import com.odeyalo.support.clients.filestorage.dto.ImageFileInformationResponseDTO;
import com.odeyalo.support.clients.filestorage.dto.VideoFileInformationResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class Video2VideoInformationResponseDTOResponseConverter implements ResponseConverter<Video, VideoInformationResponseDTO> {
    private final ImageControllerClient imageControllerClient;
    private final VideoControllerClient videoControllerClient;

    @Autowired
    public Video2VideoInformationResponseDTOResponseConverter(ImageControllerClient imageControllerClient, VideoControllerClient videoControllerClient) {
        this.imageControllerClient = imageControllerClient;
        this.videoControllerClient = videoControllerClient;
    }

    @Override
    public VideoInformationResponseDTO convert(Video video) {
        String posterFileId = video.getPosterFileId();
        ResponseEntity<ImageFileInformationResponseDTO> imageResponse = imageControllerClient.getImageInfoById(posterFileId);
        ImageFileInformationResponseDTO posterBody = imageResponse.getBody();
        if (posterBody == null) {
            throw new ImageNotFoundException(String.format("Image with id: %s was not found", posterFileId));
        }
        String videoFileId = video.getVideoFileId();
        ResponseEntity<VideoFileInformationResponseDTO> videoResponse = videoControllerClient.infoAboutVideoFile(videoFileId);
        if (videoResponse == null) {
            throw new VideoNotFoundException(String.format("Video with id: %s was not found",  videoFileId));
        }
        VideoFileInformationResponseDTO videoResponseBody = videoResponse.getBody();
        return VideoInformationResponseDTO.builder()
                .name(video.getName())
                .description(video.getDescription())
                .posterInfo(posterBody)
                .videoType(video.getVideoType())
                .videoInfo(videoResponseBody)
                .year(video.getYear())
                .build();
    }
}
