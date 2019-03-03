package com.luke_j_smith.play_sports_group.dao;

import com.luke_j_smith.play_sports_group.model.Channel;
import org.springframework.data.repository.CrudRepository;

/**
 * Channel Domain Object Repository.
 *
 * Spring will auto-implement a bean that can Create, Read, Update and Delete Channels to/from the database.
 */
public interface ChannelRepository extends CrudRepository<Channel, Integer> {

}