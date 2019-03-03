package com.luke_j_smith.play_sports_group.service;

import com.luke_j_smith.play_sports_group.dao.ChannelRepository;
import com.luke_j_smith.play_sports_group.model.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Implementation of the Channel Service.
 */
@Component
public class ChannelServiceImpl implements ChannelService {
    private Logger logger = LoggerFactory.getLogger(ChannelServiceImpl.class);

    private static final int MAX_CHANNEL_TITLE_LENGTH = 45;

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private StringManipulationService stringManipulationService;

    @Override
    public void saveChannel(final String channelName) {
        logger.info("Saving channel with name: [{}].", channelName);

        Channel channel = new Channel();

        // Ensure that the channel name isn't over the maximum length.
        channel.setChannelName(stringManipulationService.truncateString(channelName, MAX_CHANNEL_TITLE_LENGTH));

        channelRepository.save(channel);
    }
}
