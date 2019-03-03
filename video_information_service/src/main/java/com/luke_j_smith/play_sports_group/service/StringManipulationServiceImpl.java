package com.luke_j_smith.play_sports_group.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class StringManipulationServiceImpl implements StringManipulationService {
    private Logger logger = LoggerFactory.getLogger(StringManipulationServiceImpl.class);

    @Override
    public List<String> getListOfStringsInLowerCase(List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            logger.info("List of strings is null or empty.");
            return strings;
        }

        logger.info("Converting list of strings to lowercase: [{}]", strings);

        return strings.stream().map(String::toLowerCase).collect(Collectors.toList());
    }
}
