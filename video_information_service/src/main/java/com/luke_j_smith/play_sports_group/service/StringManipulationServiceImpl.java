package com.luke_j_smith.play_sports_group.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementation of the String Manipulation Service.
 */
@Component
public class StringManipulationServiceImpl implements StringManipulationService {
    private Logger logger = LoggerFactory.getLogger(StringManipulationServiceImpl.class);

    @Override
    public List<String> getListOfStringsInLowerCase(final List<String> strings) {
        if (strings == null || strings.isEmpty()) {
            logger.info("List of strings is null or empty.");
            return strings;
        }

        logger.info("Converting list of strings to lowercase: [{}]", strings);

        return strings.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    @Override
    public String joinStringsWithOr(final List<String> strings) {
        logger.info("Joining strings: [{}] together with or symbol (i.e., |).", strings);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < strings.size(); i++) {
            stringBuilder.append(strings.get(i));
            // We only want their to be the or symbol between the strings.
            if (i < strings.size() - 1) {
                stringBuilder.append("|");
            }
        }

        return stringBuilder.toString();
    }

    @Override
    public String truncateString(final String string, final int maxLength) {
        if (string == null || string.length() <= maxLength) {
            return string;
        }

        // If the string is over the maximum length, only return the first part.
        return string.substring(0, maxLength - 1);
    }
}
