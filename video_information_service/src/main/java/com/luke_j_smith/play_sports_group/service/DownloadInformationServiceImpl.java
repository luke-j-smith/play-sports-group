package com.luke_j_smith.play_sports_group.service;

import com.google.api.services.youtube.model.SearchResult;
import com.google.api.services.youtube.model.SearchResultSnippet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private YouTubeQueryService youTubeQueryService;

    @Autowired
    private FileReaderService fileReaderService;

    @Autowired
    private StringManipulationService stringManipulationService;

    @Autowired
    private ChannelService channelService;

    @Autowired
    private VideoService videoService;

    @Value("${channel_list.file.location}")
    private String channelsFile;

    @Value("${search_filter.file.location}")
    private String searchFilterFile;

    @Override
    public boolean downloadVideoInformation() {
        logger.info("Downloading video information.");

        // First we get the channel names, ...
        List<String> channelNames = getChannelNames();
        // Save that information the database, ...
        saveChannelInformation(channelNames);
        // and and query the YouTube API to find their IDs.
        List<String> channelIds = getChannelIds(channelNames);
        // Then we get all of the search terms, ...
        List<String> searchTerms = getSearchTerms();
        // and using the channel IDs and those terms, we find the matching videos.
        List<SearchResult> searchResults = getVideoSearchResultList(channelIds, searchTerms);
        // Finally we save that information to the database.
        saveAllVideoInformation(searchResults);

        return true;
    }

    /**
     * Gets the channel names specified in the 'channel_list' file in resources.
     *
     * @return a list of channel names in lower case
     */
    private List<String> getChannelNames() {
        logger.info("Getting the list of channel names to search through.");

        List<String> channelNames = fileReaderService.getFileContentsLineByLine(channelsFile);

        return stringManipulationService.getListOfStringsInLowerCase(channelNames);
    }

    /**
     * Save all of the channel information to the database.
     *
     * @param channelNames
     */
    private void saveChannelInformation(final List<String> channelNames) {
        logger.info("Saving the channel information.");

        for (String channelName : channelNames) {
            channelService.saveChannel(channelName);
        }
    }

    /**
     * Gets the channel IDs to be used when searching for videos.
     *
     * @param channelNames
     * @return a list of channel IDs
     */
    private List<String> getChannelIds(final List<String> channelNames) {
        logger.info("Getting the channel IDs.");

        return youTubeQueryService.getChannelIds(channelNames);
    }

    /**
     * Get all of the search terms specified in the search_filter file.
     *
     * @return a list of search terms
     */
    private List<String> getSearchTerms() {
        logger.info("Getting the search terms specified in the file.");

        return fileReaderService.getFileContentsLineByLine(searchFilterFile);
    }

    /**
     * Rather than searching for videos matching each term individually, we create a single search term that joins all
     * of the terms together in the format: 'luke smith|cycling|road'.
     *
     * @return a combined search term
     */
    private String getCombinedSearchTerm() {
        logger.info("Getting the combined search term from those specified in the file.");

        List<String> searchTerms = getSearchTerms();

        return stringManipulationService.joinStringsWithOr(searchTerms);
    }

    /**
     * Given all the channel IDs and all the search terms, get all the matching results.
     *
     * @param channelIds
     * @param searchTerms
     * @return a list of all video search results
     */
    private List<SearchResult> getVideoSearchResultList(final List<String> channelIds, final List<String> searchTerms) {
        logger.info("Getting all video results for all channel IDs and all search terms.");

        List<SearchResult> allVideoSearchResults = new ArrayList<>();

        for (String searchTerm : searchTerms) {
            allVideoSearchResults.addAll(getVideoSearchResultList(channelIds, searchTerm));
        }

        return allVideoSearchResults;
    }

    /**
     * Given all the channel IDs we are interested in and the search term, get all the matching results.
     *
     * @param channelIds
     * @param searchTerm
     * @return a list of all video search results
     */
    private List<SearchResult> getVideoSearchResultList(final List<String> channelIds, final String searchTerm) {
        List<SearchResult> videoSearchResults = new ArrayList<>();

        for (String channelId : channelIds) {
            logger.info("Getting search results for channel ID: [{}] and search term: [{}].", channelId, searchTerm);
            videoSearchResults.addAll(youTubeQueryService.getVideoSearchResults(channelId, searchTerm));
        }

        return videoSearchResults;
    }

    /**
     * Save all of the search results to the database.
     *
     * @param videoSearchResults
     */
    private void saveAllVideoInformation(final List<SearchResult> videoSearchResults) {
        logger.info("Saving the video search results.");

        for (SearchResult videoSearchResult : videoSearchResults) {
            SearchResultSnippet videoSnippet = videoSearchResult.getSnippet();
            videoService.saveVideo(videoSnippet.getTitle(), videoSnippet.getPublishedAt());
        }
    }
}
