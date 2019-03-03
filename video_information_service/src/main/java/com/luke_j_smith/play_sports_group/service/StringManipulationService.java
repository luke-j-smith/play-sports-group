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
    public List<String> getListOfStringsInLowerCase(List<String> strings);
}
