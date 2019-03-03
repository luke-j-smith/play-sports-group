package com.luke_j_smith.play_sports_group.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Implementation of the Download Information Service.
 */
@Component
public class DownloadInformationServiceImpl implements DownloadInformationService {
    private Logger logger = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Autowired
    YouTubeQueryService youTubeQueryService;

    @Autowired
    FileReaderService fileReaderService;

    @Autowired
    StringManipulationService stringManipulationService;

    private static final String CHANNELS_FILE = "search-properties/channel_list";

    private static final String SEARCH_FILTER_FILE = "search-properties/search_filter";

    @Override
    public void downloadVideoInformation() {
        List<String> channelIds = getChannelIds();
        for (String id : channelIds) {
            logger.info("Channel ID: [{}]", id);
        }
    }

    /**
     * Gets the channel IDs to be used when searching for videos.
     *
     * @return a list of channel IDs
     */
    private List<String> getChannelIds() {
        logger.info("Getting the channel IDs.");

        return youTubeQueryService.getChannelIds(getChannelNames());
    }

    /**
     * Gets the channel names specified in the 'channel_list' file in resources.
     *
     * @return a list of channel names in lower case
     */
    private List<String> getChannelNames() {
        logger.info("Getting the list of channel names to search through.");

        List<String> channelNames = fileReaderService.getFileContentsLineByLine(CHANNELS_FILE);

        return stringManipulationService.getListOfStringsInLowerCase(channelNames);
    }
}
