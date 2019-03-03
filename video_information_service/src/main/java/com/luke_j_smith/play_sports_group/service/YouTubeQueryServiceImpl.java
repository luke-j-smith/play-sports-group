package com.luke_j_smith.play_sports_group.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.SearchListResponse;
import com.google.api.services.youtube.model.SearchResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.api.services.youtube.YouTube;
import org.springframework.stereotype.Component;
import com.google.api.services.youtube.model.ChannelListResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the YouTube Query Service.
 */
@Component
public class YouTubeQueryServiceImpl implements YouTubeQueryService {
    Logger logger = LoggerFactory.getLogger(YouTubeQueryServiceImpl.class);

    private static final String APPLICATION_NAME = "video-information-application";

    private static final String API_KEY = "AIzaSyAsmKbjsSAXARfIZ9XO0RmvU4iLMnU3dCc";

    private static final String QUERY_PART_ID = "id";

    private static final String QUERY_PART_SNIPPET = "snippet";

    private static final String VIDEO_SEARCH_TYPE = "video";

    private static final String BASIC_VIDEO_FIELDS = "items(snippet/title, snippet/publishedAt)";

    private static final int EXPECTED_INDEX_OF_ITEMS_IN_RESULT = 0;

    private static final String EMPTY_STRING = "";

    private static YouTube youTube;

    @Override
    public List<String> getChannelIds(final List<String> channelNames) {
        logger.info("Getting channel ID for the following channels: [{}].", channelNames);

        List<String> channelIds = new ArrayList<>();

        try {
            logger.info("Ensuring that the YouTube service is initialized.");
            initializeYouTubeService();
        } catch (IOException e) {
            logger.info("Unable to initialize YouTube service: [{}].", e);
            return channelIds;
        }

        for (String channelName : channelNames) {
            try {
                channelIds.add(getChanelId(channelName));
            } catch (IOException e) {
                logger.info("IOException: [{}], for channel with name: [{}].", e, channelName);
            }
        }

        return channelIds;
    }

    @Override
    public List<SearchResult> getVideoSearchResults(final String channelId, final String searchTerm) {
        logger.info("Getting video search results for channel ID: [{}], and search term: [{}].", channelId, searchTerm);

        List<SearchResult> videoSearchResults = new ArrayList<>();

        try {
            logger.info("Ensuring that the YouTube service is initialized.");
            initializeYouTubeService();
        } catch (IOException e) {
            logger.info("Unable to initialize YouTube service: [{}].", e);
            return videoSearchResults;
        }

        try {
            videoSearchResults = getSearchResults(channelId, searchTerm, VIDEO_SEARCH_TYPE, BASIC_VIDEO_FIELDS);
        } catch (IOException e) {
            logger.info("Unable to conduct search - IOException: [{}].", e);
        }

        return videoSearchResults;
    }

    /**
     * Initialize the YouTube service if and only if it is currently null.
     *
     * @throws IOException
     */
    private void initializeYouTubeService() throws IOException {
        // Ensures that the YouTube Service is only initialized once.
        if (youTube == null) {
            logger.info("Initialising the YouTube service for the first time.");
            youTube = getYouTubeService();
        }
    }

    /**
     * Builds the YouTube service.
     *
     * @return a YouTube service
     * @throws IOException
     */
    private YouTube getYouTubeService() throws IOException {
        logger.info("Building YouTube service.");

        return new YouTube.Builder(new NetHttpTransport(), new JacksonFactory(), new HttpRequestInitializer() {
            public void initialize(HttpRequest request) throws IOException {}
        }).setApplicationName(APPLICATION_NAME).build();
    }

    /**
     * Given channel name, query YouTube to find the channel ID.
     *
     * @param channelName
     * @return the channel ID
     * @throws IOException
     */
    private String getChanelId(final String channelName) throws IOException {
        logger.info("Getting channel ID for channel with name: [{}]", channelName);

        // We are only interested in the channel ID, so can ignore any other information.
        YouTube.Channels.List channelsListRequest = youTube.channels().list(QUERY_PART_ID);
        channelsListRequest.setForUsername(channelName);
        channelsListRequest.setKey(API_KEY);

        ChannelListResponse response = channelsListRequest.execute();

        if (response == null || response.getItems() == null || response.getItems().size() == 0) {
            logger.info("Cannot get channel ID as response is not in the correct format.");

            return EMPTY_STRING;
        }

        return response.getItems().get(EXPECTED_INDEX_OF_ITEMS_IN_RESULT).getId();
    }

    /**
     * Given a channel ID, a search term, the type of content to search and the fields we are interested in, query
     * YouTube to find the matching search results.
     *
     * @param channelId
     * @param searchTerm
     * @param type
     * @param fields
     * @return a list of search results
     * @throws IOException
     */
    private List<SearchResult> getSearchResults(final String channelId, final String searchTerm,
                                                final String type, final String fields) throws IOException {
        logger.info("Searching for videos for channel ID: [{}], search term: [{}], type: [{}], and fields: [{}].",
                channelId, searchTerm, type);

        YouTube.Search.List searchListRequest = youTube.search().list(QUERY_PART_SNIPPET);
        searchListRequest.setChannelId(channelId);
        searchListRequest.setQ(searchTerm);
        searchListRequest.setType(type);
        searchListRequest.setFields(fields);
        searchListRequest.setKey(API_KEY);

        SearchListResponse response = searchListRequest.execute();

        if (response == null) {
            logger.info("Search response is null.");
            return new ArrayList<>();
        }

        return response.getItems();
    }
}
