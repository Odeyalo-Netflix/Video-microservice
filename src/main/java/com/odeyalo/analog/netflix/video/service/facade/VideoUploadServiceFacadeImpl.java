package com.odeyalo.analog.netflix.video.service.facade;

import com.odeyalo.analog.netflix.video.dto.UploadVideoDTO;
import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.exceptions.PosterUploadException;
import com.odeyalo.analog.netflix.video.exceptions.VideoUploadException;
import com.odeyalo.analog.netflix.video.service.AsyncVideoFileUploader;
import com.odeyalo.analog.netflix.video.service.VideoPosterSaverService;
import com.odeyalo.analog.netflix.video.service.VideoSaverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class VideoUploadServiceFacadeImpl implements VideoUploadServiceFacade {
    private final VideoSaverService videoSaverService;
    private final VideoPosterSaverService posterSaverService;
    @Autowired
    AsyncVideoFileUploader uploader;
    @Autowired
    public VideoUploadServiceFacadeImpl(VideoSaverService videoSaverService, VideoPosterSaverService posterSaverService) {
        this.videoSaverService = videoSaverService;
        this.posterSaverService = posterSaverService;
    }

    // Todo: Maybe rewrite it using saga pattern using events
    //  such VIDEO_SUCCESS_UPLOADED_EVENT - listen to this event using kafka and
    //  continue work when everything is success
    @Override
    public void uploadVideo(UploadVideoDTO dto, MultipartFile video, MultipartFile poster) throws PosterUploadException, VideoUploadException {
        String posterUrl = this.posterSaverService.saveImage(poster);
        dto.setPoster(posterUrl);
        UploadVideoData videoData = UploadVideoData.toUploadVideoData(dto);
        this.uploader.uploadFile(video);
//        this.videoSaverService.saveVideo(videoData, video);
    }
}
