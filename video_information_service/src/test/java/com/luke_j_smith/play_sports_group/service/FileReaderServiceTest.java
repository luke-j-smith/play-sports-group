package com.luke_j_smith.play_sports_group.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileReaderServiceTest {
    private FileReaderService fileReaderService;

    private String expectedFileContentsLineOne;

    private String expectedFileContentsLineTwo;

    private String expectedFileContentsLineThree;

    private String expectedFileContents;

    @BeforeEach
    public void setup() {
        fileReaderService = new FileReaderServiceImpl();

        expectedFileContentsLineOne = "This is a";
        expectedFileContentsLineTwo = "test file for";
        expectedFileContentsLineThree = "a unit test";

        expectedFileContents = expectedFileContentsLineOne + "\n"
                + expectedFileContentsLineTwo + "\n"
                + expectedFileContentsLineThree;
    }

    @Test
    public void getFileFromResourcesTest() {
        // Setup
        String expectedFileName = "test_file";

        // Exercise
        File actualFile = fileReaderService.getFileFromResources(expectedFileName);

        // Verify
        assertTrue(actualFile.exists());
        assertTrue(actualFile.canRead());
        assertEquals(expectedFileName, actualFile.getName());
        assertEquals(expectedFileContents.length(), actualFile.length());
    }

    @Test
    public void getFileContentsLineByLineTest() throws IOException {
        // Setup
        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add(expectedFileContentsLineOne);
        expectedOutput.add(expectedFileContentsLineTwo);
        expectedOutput.add(expectedFileContentsLineThree);

        File tmpFile = File.createTempFile("test_file", ".tmp");
        FileWriter writer = new FileWriter(tmpFile);
        writer.write(expectedFileContents);
        writer.close();

        // Exercise
        List<String> actualOutput = fileReaderService.getFileContentsLineByLine(tmpFile);

        // Verify
        assertEquals(expectedOutput, actualOutput);
    }
}
