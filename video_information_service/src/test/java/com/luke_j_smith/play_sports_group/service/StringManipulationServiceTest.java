package com.luke_j_smith.play_sports_group.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringManipulationServiceTest {
    private StringManipulationService stringManipulationService;

    @BeforeEach
    public void setup() {
        stringManipulationService = new StringManipulationServiceImpl();
    }

    @Test
    public void getListOfStringsInLowerCaseTest() {
        // Setup
        String stringOne = "ALL UPPER CASE";
        String stringTwo = "MixTure of Upper and LOwer!";
        String stringThree = "all lower case";

        List<String> input = new ArrayList<>();
        input.add(stringOne);
        input.add(stringTwo);
        input.add(stringThree);

        List<String> expectedOutput = new ArrayList<>();
        expectedOutput.add(stringOne.toLowerCase());
        expectedOutput.add(stringTwo.toLowerCase());
        expectedOutput.add(stringThree.toLowerCase());

        // Exercise
        List<String> actualOutput = stringManipulationService.getListOfStringsInLowerCase(input);

        // Verify
        assertEquals(expectedOutput, actualOutput);
    }
}
