package com.luke_j_smith.play_sports_group.service;

import com.google.api.client.util.DateTime;
import com.luke_j_smith.play_sports_group.model.Video;
import java.util.List;

public interface VideoService {
    /**
     * Used to save a video to the database.
     *
     * @param title
     * @param date
     */
    public void saveVideo(final String title, final DateTime date);

    /**
     * Used to get all of the videos saved in the database.
     *
     * @return a list of all videos in the database
     */
    public List<Video> getAllVideos();
}
