package com.example.personalQualityTracker.development.presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.personalQualityTracker.development.application.AssessmentService;
import com.example.personalQualityTracker.development.domain.Assessment;
import com.example.personalQualityTracker.development.domain.Enum.question.QuestionOne;
import com.example.personalQualityTracker.development.domain.Enum.question.QuestionTwo;
import com.example.personalQualityTracker.development.presentation.DTO.AssessmentDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


class AssessmentControllerTest {
    @Mock
    private AssessmentService assessmentService;

    @InjectMocks
    private AssessmentController assessmentController;

    public AssessmentControllerTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getAssessmentByPersonId_ReturnsAssessment() {
        // Arrange
        Long personId = 1L;
        Assessment expectedAssessment = new Assessment();

        when(assessmentService.getAssessmentByPersonId(personId)).thenReturn(expectedAssessment);

        // Act
        Assessment result = assessmentController.getAssessmentByPersonId(personId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedAssessment, result);
        verify(assessmentService, times(1)).getAssessmentByPersonId(personId);
    }

    @Test
    public void checkIfAssessmentIsMade_ReturnsTrue() {
        // Arrange
        Long personId = 1L;

        when(assessmentService.checkIfAssessmentIsMade(personId)).thenReturn(true);

        // Act
        boolean result = assessmentController.checkIfAssessmentIsMade(personId);

        // Assert
        assertTrue(result);
        verify(assessmentService, times(1)).checkIfAssessmentIsMade(personId);
    }

    @Test
    public void getPersonality_ReturnsAssessment() {
        // Arrange
        Long personId = 1L;
        Assessment expectedAssessment = new Assessment();

        when(assessmentService.getPersonality(personId)).thenReturn(expectedAssessment);

        // Act
        Assessment result = assessmentController.getPersonality(personId);

        // Assert
        assertNotNull(result);
        assertEquals(expectedAssessment, result);
        verify(assessmentService, times(1)).getPersonality(personId);
    }

    @Test
    public void addCareerPathToEmployee_ReturnsAssessmentWithHttpStatusOk() {
        // Arrange
        Long employeeId = 1L;
        Long careerPathId = 2L;
        Assessment expectedAssessment = new Assessment();

        when(assessmentService.addCareerPathToAssessment(employeeId, careerPathId)).thenReturn(expectedAssessment);

        // Act
        ResponseEntity<Assessment> response = assessmentController.addCareerPathToEmployee(employeeId, careerPathId);

        // Assert
        assertNotNull(response);
        assertEquals(expectedAssessment, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(assessmentService, times(1)).addCareerPathToAssessment(employeeId, careerPathId);
    }

    @Test
    public void getMatchingCourses_ReturnsMatchingCourses() {
        // Arrange
        Long id = 1L;
        Map<String, Integer> expectedCourses = new HashMap<>();
        expectedCourses.put("Course A", 80);
        expectedCourses.put("Course B", 70);

        when(assessmentService.findMatchingCourses(id)).thenReturn(expectedCourses);

        // Act
        Map<String, Integer> result = assessmentController.getMatchingCourses(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCourses, result);
        verify(assessmentService, times(1)).findMatchingCourses(id);
    }

    @Test
    public void getMatchingCoursesHardSkill_ReturnsMatchingCourses() {
        // Arrange
        Long id = 1L;
        Map<String, Integer> expectedCourses = new HashMap<>();
        expectedCourses.put("Course X", 90);
        expectedCourses.put("Course Y", 85);

        when(assessmentService.findMatchHardSkill(id)).thenReturn(expectedCourses);

        // Act
        Map<String, Integer> result = assessmentController.getMatchingCoursesHardSkill(id);

        // Assert
        assertNotNull(result);
        assertEquals(expectedCourses, result);
        verify(assessmentService, times(1)).findMatchHardSkill(id);
    }

}