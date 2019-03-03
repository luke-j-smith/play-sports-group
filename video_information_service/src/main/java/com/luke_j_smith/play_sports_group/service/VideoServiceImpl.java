package com.luke_j_smith.play_sports_group.service;

import com.google.api.client.util.DateTime;
import com.luke_j_smith.play_sports_group.dao.VideoRepository;
import com.luke_j_smith.play_sports_group.model.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Video Service.
 */
@Component
public class VideoServiceImpl implements VideoService {
    private Logger logger = LoggerFactory.getLogger(VideoServiceImpl.class);

    private static final int MAX_VIDEO_TITLE_LENGTH = 100;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private StringManipulationService stringManipulationService;

    @Override
    public void saveVideo(final String title, final DateTime date) {
        logger.info("Saving video with title: [{}], and date: [{}].", title, date.toString());

        Video video = new Video();

        // Ensure that the title isn't over the maximum length.
        video.setTitle(stringManipulationService.truncateString(title, MAX_VIDEO_TITLE_LENGTH));
        video.setDate(date);

        videoRepository.save(video);
    }

    @Override
    public List<Video> getAllVideos() {
        logger.info("Getting all videos.");

        // We want to return a List<Video>, but findAll() returns Iterable<Video>.
        List<Video> allVideos = new ArrayList<>();
        // So we use a lambda expression to add each video to our list.
        videoRepository.findAll().forEach(allVideos::add);

        return allVideos;
    }

    @Override
    public Video getVideo(Integer id) {
        logger.info("Getting video with ID: [{}].", id);

        if (id == null || id < 0) {
            logger.info("Invalid ID provided, returning null.");
            return null;
        }

        // If the video for the specified ID doesn't exist, return null.
        return videoRepository.findById(id).orElse(null);
    }
}
