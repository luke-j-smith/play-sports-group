package com.luke_j_smith.play_sports_group.service;

import java.io.File;
import java.util.List;

/**
 * Interface for the File Reader Service - handles any file related operations.
 */
public interface FileReaderService {
    /**
     * Get a File object from a location in resources.
     *
     * @param fileInResources
     * @return the file at the specified location
     */
    public File getFileFromResources(final String fileInResources);

    /**
     * Get the contents of a file on a line by line basis given a File.
     *
     * @param file
     * @return each line of the file as a string
     */
    public List<String> getFileContentsLineByLine(final File file);

    /**
     * Get the contents of a file on a line by line basis given a location in resources.
     *
     * @param fileInResources
     * @return each line of the file as a string
     */
    public List<String> getFileContentsLineByLine(final String fileInResources);
}
