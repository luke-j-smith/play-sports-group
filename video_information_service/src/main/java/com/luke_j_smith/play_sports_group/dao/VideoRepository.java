package com.luke_j_smith.play_sports_group.dao;

import com.luke_j_smith.play_sports_group.model.Video;
import org.springframework.data.repository.CrudRepository;

/**
 * Video Domain Object Repository.
 *
 * Spring will auto-implement a bean that can Create, Read, Update and Delete Videos to/from the database.
 */
public interface VideoRepository extends CrudRepository<Video, Integer> {

}