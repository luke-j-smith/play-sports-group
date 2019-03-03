package com.luke_j_smith.play_sports_group.controller;

import com.luke_j_smith.play_sports_group.model.Video;
import com.luke_j_smith.play_sports_group.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {
    private Logger logger = LoggerFactory.getLogger(VideoController.class);

    @Autowired
    VideoService videoService;

    @RequestMapping(value = "view-all", method = RequestMethod.GET)
    @ResponseBody
    public List<Video> getAllSavedVideoInformation() {
        logger.info("GET request to retrieve all videos save in the database.");

        List<Video> allVideos = videoService.getAllVideos();

        if (allVideos == null) {
            logger.info("VideoService returned null unexpectedly, returning an empty list instead.");
            return new ArrayList<>();
        }

        return allVideos;
    }
}
