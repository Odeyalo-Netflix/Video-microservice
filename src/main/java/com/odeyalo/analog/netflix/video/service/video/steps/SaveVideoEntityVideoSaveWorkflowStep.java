package com.odeyalo.analog.netflix.video.service.video.steps;

import com.odeyalo.analog.netflix.video.dto.UploadVideoData;
import com.odeyalo.analog.netflix.video.entity.Video;
import com.odeyalo.analog.netflix.video.entity.VideoType;
import com.odeyalo.analog.netflix.video.service.VideoSaverService;
import com.odeyalo.analog.netflix.video.service.video.dto.UploadVideoInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.time.LocalDate;

@Service
@Order(value = 3)
public class SaveVideoEntityVideoSaveWorkflowStep implements VideoSaveWorkflowStep {
    private final VideoSaverService saverService;
    private final Logger logger = LoggerFactory.getLogger(SaveVideoEntityVideoSaveWorkflowStep.class);

    @Autowired
    public SaveVideoEntityVideoSaveWorkflowStep(VideoSaverService saverService) {
        this.saverService = saverService;
    }

    @Override
    public void process(UploadVideoInformation information, Video rawVideo) {
        this.logger.info("Saving entity step. Information: {}, raw video: {}", information, rawVideo);
        String videoFileId = rawVideo.getVideoFileId();
        String posterFileId = rawVideo.getPosterFileId();
        Assert.notNull(videoFileId, "Video file id cannot be null!");
        Assert.notNull(posterFileId, "Poster file id cannot be null!");
        Video builtVideo = buildVideo(information, rawVideo);
        Video saved = this.saverService.saveVideo(builtVideo);
        //Copy values from saved video to raw video for next saga steps(Without it some fields such video name or description will be null)
        rawVideo.copy(saved);
        this.logger.info("Successful saved video: {}", saved);
    }

    private Video buildVideo(UploadVideoInformation information, Video rawVideo) {
        UploadVideoData data = information.getData();
        String description = data.getDescription();
        VideoType videoType = data.getVideoType();
        String videoName = data.getVideoName();
        LocalDate year = data.getYear();
        return Video.from(rawVideo).videoType(videoType).description(description).name(videoName).year(year).build();
    }
}
