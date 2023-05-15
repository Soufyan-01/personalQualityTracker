package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class CareerPathAdviseTest {

    private CareerPathAdvise careerPathAdvise;

    @BeforeEach
    void setUp() {
        careerPathAdvise = new CareerPathAdvise();
    }

    @Test
    void findPercentageByCareerPath() {
        // Create sample data
        List<CareerPath> careerPaths = new ArrayList<>();
        // Add career paths to the list

        Interest interestOne = Interest.REALISTIC;
        Interest interestTwo = Interest.INVESTIGATIVE;
        Interest interestThree = Interest.ARTISTIC;
        int interestPercentageOne = 50;
        int interestPercentageTwo = 30;
        int interestPercentageThree = 20;

        // Invoke the method being tested
        HashMap<String, Integer> result = careerPathAdvise.findPercentageByCareerPath(
                careerPaths, interestOne, interestTwo, interestThree,
                interestPercentageOne, interestPercentageTwo, interestPercentageThree
        );

        // Assert the result
        // Add appropriate assertions based on your expected output

        // Example assertions
        assertEquals(0, result.size()); // Check if the result is empty for the provided empty careerPaths list

        // Create a career path with matching interests and importance
        CareerPath careerPath = new CareerPath("Test Career Path", "Description", List.of(interestOne, interestTwo));
        careerPaths.add(careerPath);

        result = careerPathAdvise.findPercentageByCareerPath(
                careerPaths, interestOne, interestTwo, interestThree,
                interestPercentageOne, interestPercentageTwo, interestPercentageThree
        );

        assertEquals(1, result.size()); // Check if the result contains one career path
        assertEquals(Integer.valueOf(100), result.get("Test Career Path")); // Check the matching percentage for the career path

        // Create a career path with matching interestThree and importance
        CareerPath careerPathWithInterestThree = new CareerPath("Test Career Path 2", "Description", List.of(interestThree));
        careerPaths.add(careerPathWithInterestThree);

        result = careerPathAdvise.findPercentageByCareerPath(
                careerPaths, interestOne, interestTwo, interestThree,
                interestPercentageOne, interestPercentageTwo, interestPercentageThree
        );

        assertEquals(2, result.size()); // Check if the result contains two career paths
        assertEquals(Integer.valueOf(80), result.get("Test Career Path")); // Check the matching percentage for the first career path
        assertEquals(Integer.valueOf(20), result.get("Test Career Path 2")); // Check the matching percentage for the second career path

        // Add more assertions as per your requirements and expected behavior
    }


    @Test
    void testToString() {
        CareerPath careerPath = new CareerPath();
        careerPath.setName("Test Career Path");
        CareerPathAdvise careerPathAdvise = new CareerPathAdvise(careerPath);

        String expectedToString = "CareerPathAdvise{careerPath=Test Career Path}";
        String actualToString = careerPathAdvise.toString();

        Assertions.assertEquals(expectedToString, actualToString);
    }

    @Test
    void getId() {
        Long id = 1L;
        CareerPathAdvise careerPathAdvise = new CareerPathAdvise();
        careerPathAdvise.setId(id);
        Assertions.assertEquals(id, careerPathAdvise.getId());
    }

    @Test
    void getCareerPath() {
        CareerPath careerPath = new CareerPath();
        careerPath.setName("Test Career Path");
        CareerPathAdvise careerPathAdvise = new CareerPathAdvise(careerPath);
        Assertions.assertEquals(careerPath, careerPathAdvise.getCareerPath());
    }

    @Test
    void setId() {
        Long id = 1L;
        CareerPathAdvise careerPathAdvise = new CareerPathAdvise();
        careerPathAdvise.setId(id);
        Assertions.assertEquals(id, careerPathAdvise.getId());
    }

    @Test
    void setCareerPath() {
        CareerPath careerPath = new CareerPath();
        careerPath.setName("Test Career Path");
        CareerPathAdvise careerPathAdvise = new CareerPathAdvise();
        careerPathAdvise.setCareerPath(careerPath);
        Assertions.assertEquals(careerPath, careerPathAdvise.getCareerPath());
    }

}