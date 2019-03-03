package com.luke_j_smith.play_sports_group.controller;

import com.luke_j_smith.play_sports_group.service.DownloadInformationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("/download-information")
public class DownloadInformationController {
    private Logger logger = LoggerFactory.getLogger(DownloadInformationController.class);

    @Autowired
    DownloadInformationService downloadInformationService;

    @RequestMapping(value = "video", method = RequestMethod.GET)
    public HttpStatus downloadVideoInformation() {
        logger.info("GET request to download all video information.");

        boolean downloadSuccessful = downloadInformationService.downloadVideoInformation();

        if (downloadSuccessful) {
            return HttpStatus.OK;
        }

        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
