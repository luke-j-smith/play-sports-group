package com.luke_j_smith.play_sports_group.controller;

import com.luke_j_smith.play_sports_group.service.DownloadInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RestController
@RequestMapping("api/v1")
public class DownloadInformationController {
    @Autowired
    DownloadInformationService downloadInformationService;

    @RequestMapping(value = "download", method = RequestMethod.GET)
    public String displayChannels() {
        downloadInformationService.downloadVideoInformation();
        return "Hello";
    }
}
