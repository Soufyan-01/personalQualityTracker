package com.example.personalQualityTracker.development.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.personalQualityTracker.development.data.SpringCareerPathRespository;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class CareerPathServiceTest {
    @Mock
    private SpringCareerPathRespository springCareerPathRespository;

    @InjectMocks
    private CareerPathService careerPathService;

    public CareerPathServiceTest() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void deleteCareerPath_CallsDeleteByName() {
        // Arrange
        String name = "Career Path 1";

        // Act
        careerPathService.deleteCareerPath(name);

        // Assert
        verify(springCareerPathRespository, times(1)).deleteByName(name);
    }

    @Test
    public void getAllCareerPaths_ReturnsAllCareerPaths() {
        // Arrange
        List<CareerPath> expectedCareerPaths = Arrays.asList(
                new CareerPath("Career Path 1", "Description 1", Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE)),
                new CareerPath("Career Path 2", "Description 2", Arrays.asList(Interest.SOCIAL, Interest.ENTERPRISING))
        );

        when(springCareerPathRespository.findAll()).thenReturn(expectedCareerPaths);

        // Act
        List<CareerPath> result = careerPathService.getAllCareerPaths();

        // Assert
        assertEquals(expectedCareerPaths, result);
        verify(springCareerPathRespository, times(1)).findAll();
    }

    @Test
    public void getCareerPathById_ReturnsCareerPathOptional() {
        // Arrange
        Long careerPathId = 1L;
        CareerPath expectedCareerPath = new CareerPath("Career Path 1", "Description 1", Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE));

        when(springCareerPathRespository.findById(careerPathId)).thenReturn(Optional.of(expectedCareerPath));

        // Act
        Optional<CareerPath> result = careerPathService.getCareerPathById(careerPathId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedCareerPath, result.get());
        verify(springCareerPathRespository, times(1)).findById(careerPathId);
    }

}