package com.odeyalo.analog.netflix.video.controller;

import com.odeyalo.analog.netflix.video.dto.UploadVideoDTO;
import com.odeyalo.analog.netflix.video.dto.VideoInformationResponseDTO;
import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.exceptions.PosterUploadException;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import com.odeyalo.analog.netflix.video.service.VideoManager;
import com.odeyalo.analog.netflix.video.service.facade.VideoUploadServiceFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.support.ResourceRegion;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collections;

@RestController
@RequestMapping("/video")
public class VideoController {
    private final VideoManager manager;
    private final Logger logger = LoggerFactory.getLogger(VideoController.class);
    private final VideoUploadServiceFacade videoUploadServiceFacade;
    private final RestTemplate restTemplate;
    @Autowired
    public VideoController(VideoManager manager, VideoUploadServiceFacade videoUploadServiceFacade, RestTemplate restTemplate) {
        this.manager = manager;
        this.videoUploadServiceFacade = videoUploadServiceFacade;
        this.restTemplate = restTemplate;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Video video = this.manager.getVideo(id);
        VideoInformationResponseDTO response = VideoInformationResponseDTO.toVideoInformationResponse(video);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/watch")
    public ResponseEntity<?> streamVideo(@RequestParam(name = "v") String id, @RequestHeader(name = "range", required = false) String range) {
        HttpHeaders headers = new HttpHeaders();
        headers.setRange(Collections.singletonList(HttpRange.createByteRange(Long.parseLong(range))));
        HttpEntity<String> entity = new HttpEntity<>("body", headers);
        return this.restTemplate.exchange("http://localhost:8084/api/v1/video/watch?videoId=" + id, HttpMethod.GET, entity, ResourceRegion.class);
    }
    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadVideo(@RequestPart UploadVideoDTO dto, @RequestPart MultipartFile video, @RequestPart MultipartFile poster) throws VideoUploadException, PosterUploadException {
        this.logger.info("Dto: {}, video: {}, poster: {}", dto, video.getOriginalFilename(), poster.getOriginalFilename());
        this.videoUploadServiceFacade.uploadVideo(dto, video, poster);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        return null;
    }

}
