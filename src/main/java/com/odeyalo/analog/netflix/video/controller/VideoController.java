package com.odeyalo.analog.netflix.video.controller;

import com.odeyalo.analog.netflix.video.configuration.security.AuthenticatedUserInformation;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/video")
@CrossOrigin("*")
public class VideoController {
    private final VideoManager manager;
    private final Logger logger = LoggerFactory.getLogger(VideoController.class);
    private final VideoUploadServiceFacade videoUploadServiceFacade;

    @Autowired
    public VideoController(VideoManager manager, VideoUploadServiceFacade videoUploadServiceFacade) {
        this.manager = manager;
        this.videoUploadServiceFacade = videoUploadServiceFacade;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        Video video = this.manager.getVideo(id);
        VideoInformationResponseDTO response = VideoInformationResponseDTO.toVideoInformationResponse(video);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/user/list")
    public ResponseEntity<?> getAllVideosByUser(Authentication authentication) {
        Integer id = getUserId(authentication);
        List<Video> videos = this.manager.getVideosByUser(String.valueOf(id));
        return ResponseEntity.ok(videos);
    }


    @PostMapping(value = "/upload")
    public ResponseEntity<?> uploadVideo(@RequestPart UploadVideoDTO dto,
                                         @RequestPart MultipartFile video,
                                         @RequestPart MultipartFile poster,
                                         Authentication authentication) throws VideoUploadException, PosterUploadException {
        this.logger.info("Dto: {}, video: {}, poster: {}", dto, video.getOriginalFilename(), poster.getOriginalFilename());
        Integer userId = getUserId(authentication);
        this.videoUploadServiceFacade.uploadVideo(dto, String.valueOf(userId), video, poster);
        Map<String, Object> body = new HashMap<>();
        body.put("status", "VIDEO_UPLOADING_STARTED");
        return ResponseEntity.ok(body);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateById(@PathVariable String id) {
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id) {
        return null;
    }


    private Integer getUserId(Authentication authentication) {
        AuthenticatedUserInformation principal = (AuthenticatedUserInformation) authentication.getPrincipal();
        return principal.getId();
    }
}
