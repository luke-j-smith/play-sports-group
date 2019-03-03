package com.luke_j_smith.play_sports_group.service;

/**
 * Interface for the Channel Service - handles channel related operations.
 */
public interface ChannelService {
    /**
     * Used to save a channel to the database.
     *
     * @param channelName
     */
    public void saveChannel(final String channelName);
}
