package com.luke_j_smith.play_sports_group.controller;

import com.luke_j_smith.play_sports_group.dto.BasicVideoDTO;
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

    private static final String DELETE_SUCCESSFUL_MESSAGE = "Video successfully deleted.";

    private static final String DELETE_UNSUCCESSFUL_MESSAGE = "Unable to delete video with for the ID provided.";

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
    public ResponseEntity<Video> getSavedVideo(@RequestParam("id") final Integer id) {
        logger.info("GET request to retrieve video with ID: [{}].", id);

        Video video = videoService.getVideo(id);

        if (video == null) {
            logger.info("VideoService returned null unexpectedly, returning an empty list instead.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, JSON_RESPONSE_HEADER);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(video);
    }

    @RequestMapping(value = "delete", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteVideo(@RequestParam("id") final Integer id) {
        logger.info("DELETE request to delete video with id: [{}].", id);

        Boolean deleteSuccessful = videoService.deleteVideo(id);

        if (deleteSuccessful) {
            return ResponseEntity.status(HttpStatus.OK).body(DELETE_SUCCESSFUL_MESSAGE);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(DELETE_UNSUCCESSFUL_MESSAGE);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public ResponseEntity<List<BasicVideoDTO>> search(@RequestParam("find") final String searchTerm) {
        logger.info("GET request to get videos where the title matches: [{}].", searchTerm);

        List<BasicVideoDTO> basicVideoDTOS = videoService.getVideos(searchTerm);

        if (basicVideoDTOS == null) {
            logger.info("VideoService returned null unexpectedly, returning an empty list instead.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, JSON_RESPONSE_HEADER);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(basicVideoDTOS);
    }
}
