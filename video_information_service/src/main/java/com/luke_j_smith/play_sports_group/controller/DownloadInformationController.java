package com.luke_j_smith.play_sports_group.controller;

import com.luke_j_smith.play_sports_group.service.DownloadInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Download Information Controller - handles download requests.
 */
@RestController
@RequestMapping("/download-information")
public class DownloadInformationController {
    private Logger logger = LoggerFactory.getLogger(DownloadInformationController.class);

    private static final String DOWNLOAD_SUCCESSFUL_MESSAGE = "The download was successful :)";

    private static final String DOWNLOAD_UNSUCCESSFUL_MESSAGE = "The download was unsuccessful :(";

    @Autowired
    DownloadInformationService downloadInformationService;

    @RequestMapping(value = "video", method = RequestMethod.GET)
    public ResponseEntity<String> downloadVideoInformation() {
        logger.info("GET request to download all video information.");

        boolean downloadSuccessful = downloadInformationService.downloadVideoInformation();

        if (downloadSuccessful) {
            return ResponseEntity.status(HttpStatus.OK).body(DOWNLOAD_SUCCESSFUL_MESSAGE);
        }

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(DOWNLOAD_UNSUCCESSFUL_MESSAGE);
    }
}
