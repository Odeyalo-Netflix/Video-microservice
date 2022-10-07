package com.odeyalo.analog.netflix.video.dto;

import com.odeyalo.analog.netflix.video.entity.VideoType;
import com.odeyalo.support.clients.filestorage.dto.ImageFileInformationResponseDTO;
import com.odeyalo.support.clients.filestorage.dto.VideoFileInformationResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VideoInformationResponseDTO {
    private String name;
    private VideoType videoType;
    private String description;
    private VideoFileInformationResponseDTO videoInfo;
    private ImageFileInformationResponseDTO posterInfo;
    private LocalDate year;
}
