package com.luke_j_smith.play_sports_group.service;

import com.google.api.services.youtube.model.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
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
        String searchTerm = getSearchTerm();
        List<String> channelIds = getChannelIds();
        List<SearchResult> searchResults = new ArrayList<>();
        for (String id : channelIds) {
            logger.info("Channel ID: [{}]", id);
            searchResults.addAll(youTubeQueryService.getVideoSearchResults(id, searchTerm));
        }
        logger.info("Search Results: [{}]", searchResults);
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

    /**
     * Rather than searching for videos matching each term individually, we create a single search term that joins all
     * of the terms together in the format: 'luke smith|cycling|road'.
     *
     * @return a combined search term
     */
    private String getSearchTerm() {
        logger.info("Getting the search terms specified in the file.");

        List<String> searchTerms = fileReaderService.getFileContentsLineByLine(SEARCH_FILTER_FILE);

        return stringManipulationService.joinStringsWithOr(searchTerms);
    }
}
