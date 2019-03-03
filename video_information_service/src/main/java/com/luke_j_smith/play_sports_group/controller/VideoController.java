package com.luke_j_smith.play_sports_group.controller;

import com.luke_j_smith.play_sports_group.model.Video;
import com.luke_j_smith.play_sports_group.service.VideoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Video Controller - handles video related requests.
 */
@RestController
@RequestMapping("/video")
public class VideoController {
    private Logger logger = LoggerFactory.getLogger(VideoController.class);

    private static final String JSON_RESPONSE_HEADER = "application/json; charset=UTF-8";

    @Autowired
    VideoService videoService;

    @RequestMapping(value = "view-all", method = RequestMethod.GET)
    public ResponseEntity<List<Video>> getAllSavedVideoInformation() {
        logger.info("GET request to retrieve all videos save in the database.");

        List<Video> allVideos = videoService.getAllVideos();

        if (allVideos == null) {
            logger.info("VideoService returned null unexpectedly, returning an empty list instead.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, JSON_RESPONSE_HEADER);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(allVideos);
    }

    @RequestMapping(value = "view", method = RequestMethod.GET)
    public ResponseEntity<Video> getSavedVideo(@RequestParam("id") Integer id) {
        logger.info("GET request to retrieve all videos save in the database.");

        Video video = videoService.getVideo(id);

        if (video == null) {
            logger.info("VideoService returned null unexpectedly, returning an empty list instead.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, JSON_RESPONSE_HEADER);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(video);
    }
}
