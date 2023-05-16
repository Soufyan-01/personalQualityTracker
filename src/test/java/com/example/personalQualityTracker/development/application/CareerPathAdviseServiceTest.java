package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.domain.Assessment;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.domain.CareerPathAdvise;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CareerPathAdviseServiceTest {
    @Mock
    private CareerPathService careerPathService;

    @Mock
    private AssessmentService assessmentService;

    @Mock
    private CareerPathAdvise careerPathAdvise;

    @InjectMocks
    private CareerPathAdviseService careerPathServiceUnderTest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getCareerPathAdviseWithPercentage_ReturnsExpectedResult() {
        // Arrange
        Long id = 1L;

        String name = "Test Career Path";
        String description = "Description";
        List<Interest> interestList = List.of(Interest.REALISTIC, Interest.INVESTIGATIVE, Interest.ARTISTIC);

        // Create an instance using the constructor
        CareerPath careerPath = new CareerPath(name, description, interestList);


        List<CareerPath> careerPaths = Arrays.asList(
                new CareerPath(name, description, interestList),
        new CareerPath(name, description, interestList)
        );

        Assessment assessment = new Assessment();
        assessment.setInterestOne("Realistic");
        assessment.setInterestTwo("Artistic");
        assessment.setInterestThree("Social");
        assessment.setPercentageInterestOne(30);
        assessment.setPercentageInterestTwo(40);
        assessment.setPercentageInterestThree(30);

        HashMap<String, Integer> expectedAdvise = new HashMap<>();
        expectedAdvise.put("Career Path 1", 50);
        expectedAdvise.put("Career Path 2", 50);

        when(careerPathService.getAllCareerPaths()).thenReturn(careerPaths);
        when(assessmentService.getAssessmentByPersonId(id)).thenReturn(assessment);
        when(careerPathAdvise.findPercentageByCareerPath(
                careerPaths, Interest.REALISTIC, Interest.ARTISTIC, Interest.SOCIAL,
                30, 40, 30))
                .thenReturn(expectedAdvise);

        // Act
        HashMap<String, Integer> result = careerPathServiceUnderTest.getCareerPathAdviseWithPercentage(id);

        // Assert
        assertEquals(expectedAdvise, result);
        verify(careerPathService, times(1)).getAllCareerPaths();
        verify(assessmentService, times(1)).getAssessmentByPersonId(id);
        verify(careerPathAdvise, times(1)).findPercentageByCareerPath(
                careerPaths, Interest.REALISTIC, Interest.ARTISTIC, Interest.SOCIAL,
                30, 40, 30);
    }

}