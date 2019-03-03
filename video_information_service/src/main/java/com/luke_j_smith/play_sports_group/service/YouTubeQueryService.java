package com.luke_j_smith.play_sports_group.service;

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
    public List<String> getChannelIds(List<String> channelNames);
}
