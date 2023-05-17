package com.example.personalQualityTracker.development.presentation;

import static org.junit.jupiter.api.Assertions.*;

import com.example.personalQualityTracker.development.application.CareerPathAdviseService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CareerPathAdviseControllerTest {

    @Mock
    private CareerPathAdviseService careerPathAdviseService;

    private CareerPathAdviseController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CareerPathAdviseController(careerPathAdviseService);
    }

    @Test
    public void getCareerPathAdviseWithPercentage_ValidId_ReturnsCareerPathAdvise() {
        // Arrange
        Long id = 1L;
        HashMap<String, Integer> careerPathAdvise = new HashMap<>();
        careerPathAdvise.put("Career Path 1", 50);
        careerPathAdvise.put("Career Path 2", 30);
        careerPathAdvise.put("Career Path 3", 20);

        when(careerPathAdviseService.getCareerPathAdviseWithPercentage(id)).thenReturn(careerPathAdvise);

        // Act
        ResponseEntity<HashMap<String, Integer>> response = controller.getCareerPathAdviseWithPercentage(id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(careerPathAdvise, response.getBody());
        verify(careerPathAdviseService, times(1)).getCareerPathAdviseWithPercentage(id);
    }

}