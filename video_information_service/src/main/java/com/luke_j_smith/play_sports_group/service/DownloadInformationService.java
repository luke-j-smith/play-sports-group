package com.luke_j_smith.play_sports_group.service;

/**
 * Interface for the Download Information Service - handles downloading information.
 */
public interface DownloadInformationService {
    /**
     * Handles downloading the required information.
     *
     * @return whether the download was successful
     */
    public boolean downloadVideoInformation();
}
