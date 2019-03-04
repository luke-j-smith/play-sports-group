package com.luke_j_smith.play_sports_group.service;

import com.google.api.client.util.DateTime;
import com.luke_j_smith.play_sports_group.dto.BasicVideoDTO;
import com.luke_j_smith.play_sports_group.model.Video;
import java.util.List;

/**
 * Interface for the Video Service - handles video related operations.
 */
public interface VideoService {
    /**
     * Create - used to save a video to the database.
     *
     * @param title
     * @param date
     */
    public void saveVideo(final String title, final DateTime date);

    /**
     * Read all - used to get all of the videos saved in the database.
     *
     * @return a list of all videos in the database
     */
    public List<Video> getAllVideos();

    /**
     * Read individual - get a video given an ID, where the id is the video's ID in the database.
     *
     * @param id
     * @return the corresponding video
     */
    public Video getVideo(final Integer id);

    /**
     * Delete - delete a video from the database given an ID.
     *
     * @param id
     * @return whether the delete was successful
     */
    public Boolean deleteVideo(final Integer id);

    /**
     * Read specific - used to get a list of videos where the title matches a search term.
     *
     * @param searchTerm
     * @return a list of videos that match the search term
     */
    public List<BasicVideoDTO> getVideos(final String searchTerm);
}
