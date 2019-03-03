package com.luke_j_smith.play_sports_group.service;

import java.util.List;

/**
 * Interface for the String Manipulation Service - handles any string related operations.
 */
public interface StringManipulationService {
    /**
     * Convert a list of strings into lower case.
     *
     * @param strings
     * @return the list of strings in lower case
     */
    public List<String> getListOfStringsInLowerCase(final List<String> strings);

    /**
     * Convert a list of strings into a single string, where each string is separated with the or symbol (i.e., '|').
     *
     * @param strings
     * @return a single string with '|' separating the input strings
     */
    public String joinStringsWithOr(final List<String> strings);

    /**
     * Ensures that a string does not exceed a certain length.
     *
     * @param string
     * @param maxLength
     * @return a string that is guaranteed to be less than a given length
     */
    public String truncateString(final String string, final int maxLength);
}
