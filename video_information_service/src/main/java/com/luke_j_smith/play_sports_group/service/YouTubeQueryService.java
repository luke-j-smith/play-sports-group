package com.luke_j_smith.play_sports_group.service;

import com.google.api.services.youtube.model.SearchResult;

import java.util.List;

/**
 * Interface for the YouTube Query Service - handles any YouTube queries.
 */
public interface YouTubeQueryService {
    /**
     * Given a list of channel names, get their channel IDs.
     *
     * @param channelNames
     * @return the list of channel IDs
     */
    public List<String> getChannelIds(final List<String> channelNames);

    /**
     * Given a channel ID and a search term, get the list of results when searching through videos.
     *
     * @param channelId
     * @param searchTerm
     * @return a list of search results
     */
    public List<SearchResult> getVideoSearchResults(final String channelId, final String searchTerm);
}
