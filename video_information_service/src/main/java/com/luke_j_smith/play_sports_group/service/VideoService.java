package com.luke_j_smith.play_sports_group.service;

import com.google.api.client.util.DateTime;

public interface VideoService {
    /**
     * Used to save a video to the database.
     *
     * @param title
     * @param date
     */
    public void saveVideo(final String title, final DateTime date);
}
