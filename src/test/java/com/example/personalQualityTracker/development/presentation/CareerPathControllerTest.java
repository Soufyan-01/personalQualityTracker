package com.example.personalQualityTracker.development.presentation;

import static org.junit.jupiter.api.Assertions.*;

import com.example.personalQualityTracker.development.application.CareerPathService;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.presentation.DTO.CareerPathDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CareerPathControllerTest {


    @Mock
    private CareerPathService careerPathService;

    private CareerPathController controller;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CareerPathController(careerPathService);
    }


    @Test
    public void deleteCareerPath_ValidName_CallsServiceToDeleteCareerPath() {
        // Arrange
        String name = "Career Path 1";

        // Act
        controller.deleteCareerPath(name);

        // Assert
        verify(careerPathService, times(1)).deleteCareerPath(name);
    }

    @Test
    public void getAllCareerPaths_ReturnsListOfCareerPaths() {
        // Arrange
        CareerPath careerPath1 = new CareerPath();
        CareerPath careerPath2 = new CareerPath();
        List<CareerPath> careerPaths = Arrays.asList(careerPath1, careerPath2);
        when(careerPathService.getAllCareerPaths()).thenReturn(careerPaths);

        // Act
        List<CareerPath> result = controller.getAllCareerPaths();

        // Assert
        assertEquals(careerPaths, result);
        verify(careerPathService, times(1)).getAllCareerPaths();
    }

}