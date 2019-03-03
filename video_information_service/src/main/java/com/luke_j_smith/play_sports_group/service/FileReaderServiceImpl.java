package com.luke_j_smith.play_sports_group.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the File Reader Service.
 */
@Component
public class FileReaderServiceImpl implements FileReaderService {
    private Logger logger = LoggerFactory.getLogger(FileReaderServiceImpl.class);

    @Override
    public File getFileFromResources(String fileName) {
        logger.info("Getting file from resources with name: [{}].", fileName);

        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);
        File file = new File(resource.getFile());

        return file;
    }

    @Override
    public List<String> getFileContentsLineByLine(File file) {
        List<String> fileContents = new ArrayList<>();

        if (file == null || !file.canRead()) {
            logger.info("Unable to read file.");
            return fileContents;
        }

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                fileContents.add(scanner.nextLine());
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            logger.info("Cannot read line - FileNotFoundException: [{}].", e);
        }

        return fileContents;
    }
}
