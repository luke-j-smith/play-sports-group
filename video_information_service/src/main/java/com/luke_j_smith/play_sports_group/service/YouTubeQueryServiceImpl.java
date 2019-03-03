package com.luke_j_smith.play_sports_group.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
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

    private static final int EXPECTED_INDEX_OF_ITEMS_IN_RESULT = 0;

    private static final String EMPTY_STRING = "";

    private static YouTube youTube;

    @Override
    public List<String> getChannelIds(List<String> channelNames) {
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
     * Get the channel ID from a given channel name.
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
}
