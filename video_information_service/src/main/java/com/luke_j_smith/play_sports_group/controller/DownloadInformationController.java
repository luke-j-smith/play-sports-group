package com.luke_j_smith.play_sports_group.controller;

import com.luke_j_smith.play_sports_group.service.FileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("api/v1/download")
public class DownloadInformationController {
    @Autowired
    FileReaderService fileReaderService;

    @RequestMapping(value = "channels", method = RequestMethod.GET)
    public List<String> displayChannels() {
        String channelFileLocation = "search-properties/channel_list";
        File channelFile = fileReaderService.getFileFromResources(channelFileLocation);
        return fileReaderService.getFileContentsLineByLine(channelFile);
    }
}
