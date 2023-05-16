package com.example.personalQualityTracker.development.application;

import static org.junit.jupiter.api.Assertions.*;

import com.example.personalQualityTracker.development.data.SpringSoftSkillCourseRepository;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class SoftSkillCourseServiceTest {

    @Mock
    private SpringSoftSkillCourseRepository springSoftSkillCourseRepository;

    @Mock
    private CareerPathService careerPathService;
    @InjectMocks

    private SoftSkillCourseService softSkillCourseService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        softSkillCourseService = new SoftSkillCourseService(springSoftSkillCourseRepository, careerPathService);
    }

    @Test
    void getSoftSkillCourseById_ValidId_ReturnsSoftSkillCourse() {
        // Arrange
        Long courseId = 1L;
        SoftSkillCourse softSkillCourse = new SoftSkillCourse();
        when(springSoftSkillCourseRepository.findById(courseId)).thenReturn(Optional.of(softSkillCourse));

        // Act
        SoftSkillCourse result = softSkillCourseService.getSoftSkillCourseById(courseId);

        // Assert
        Assertions.assertEquals(softSkillCourse, result);
        verify(springSoftSkillCourseRepository, times(1)).findById(courseId);
    }

    @Test
    void getAllSoftSkillCourses_ReturnsAllSoftSkillCourses() {
        // Arrange
        List<SoftSkillCourse> softSkillCourses = new ArrayList<>();
        softSkillCourses.add(new SoftSkillCourse());
        when(springSoftSkillCourseRepository.findAll()).thenReturn(softSkillCourses);

        // Act
        List<SoftSkillCourse> result = softSkillCourseService.getAllSoftSkillCourses();

        // Assert
        Assertions.assertEquals(softSkillCourses, result);
        verify(springSoftSkillCourseRepository, times(1)).findAll();
    }

    @Test
    void getSoftSkillCourseBasedOnId_ValidId_ReturnsSoftSkillCourse() {
        // Arrange
        Long courseId = 1L;
        SoftSkillCourse softSkillCourse = new SoftSkillCourse();
        when(springSoftSkillCourseRepository.getById(courseId)).thenReturn(softSkillCourse);

        // Act
        SoftSkillCourse result = softSkillCourseService.getSoftSkillCourseBasedOnId(courseId);

        // Assert
        Assertions.assertEquals(softSkillCourse, result);
        verify(springSoftSkillCourseRepository, times(1)).getById(courseId);
    }

    @Test
    void getSoftSkillCourseByName_ValidName_ReturnsCourseId() {
        // Arrange
        String courseName = "CourseName";
        Long courseId = 1L;
        SoftSkillCourse softSkillCourse = new SoftSkillCourse();
        softSkillCourse.setId(courseId);
        when(springSoftSkillCourseRepository.findSoftSkillCourseByCourseName(courseName)).thenReturn(Optional.of(softSkillCourse));

        // Act
        Long result = softSkillCourseService.getSoftSkilLCourseByName(courseName);

        // Assert
        Assertions.assertEquals(courseId, result);
        verify(springSoftSkillCourseRepository, times(1)).findSoftSkillCourseByCourseName(courseName);
    }

    @Test
    void createNewSoftSkill_ValidData_ReturnsCreatedSoftSkillCourse() {
        // Arrange
        String courseName = "CourseName";
        String courseDescription = "CourseDescription";
        int courseLevel = 2;
        List<Interest> interestList = new ArrayList<>();
        SoftSkillCourse softSkillCourse = new SoftSkillCourse(courseName, courseDescription, courseLevel, interestList);
        when(springSoftSkillCourseRepository.save(any(SoftSkillCourse.class))).thenReturn(softSkillCourse);

        // Act
        SoftSkillCourse result = softSkillCourseService.createNewSoftSkill(courseName, courseDescription, courseLevel, interestList);

        // Assert
        Assertions.assertEquals(softSkillCourse, result);
        verify(springSoftSkillCourseRepository, times(1)).save(any(SoftSkillCourse.class));
    }


    @Test
    void addCareerPathsToSoftSkillCourse_ValidData_ReturnsUpdatedCourse() {
        // Arrange
        Long courseId = 1L;
        List<Long> careerPathIds = List.of(1L, 2L, 3L);

        String courseName = "CourseName";
        String courseDescription = "CourseDescription";
        int courseLevel = 2;
        List<Interest> interestList = new ArrayList<>();

        SoftSkillCourse existingCourse = new SoftSkillCourse(courseName, courseDescription, courseLevel, interestList);

        String name = "Test Career Path";
        String description = "Description";
        List<Interest> interestList2 = List.of(Interest.REALISTIC, Interest.INVESTIGATIVE, Interest.ARTISTIC);

        List<CareerPath> careerPaths = List.of(
                new CareerPath("Career Path 1", "oke", java.util.Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE)),
                new CareerPath("Career Path 2", "oke", java.util.Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE)),
                new CareerPath("Career Path 3", "oke", java.util.Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE))
        );

        CareerPath careerPath1 = careerPaths.get(0);
        CareerPath careerPath2 = careerPaths.get(1);
        CareerPath careerPath3 = careerPaths.get(2);

        when(springSoftSkillCourseRepository.save(any(SoftSkillCourse.class))).thenReturn(existingCourse);
        when(careerPathService.getCareerPathById(1L)).thenReturn(Optional.of(careerPath1));
        when(careerPathService.getCareerPathById(2L)).thenReturn(Optional.of(careerPath2));
        when(careerPathService.getCareerPathById(3L)).thenReturn(Optional.of(careerPath3));
        when(springSoftSkillCourseRepository.findById(courseId)).thenReturn(Optional.of(existingCourse));

        // Act
        SoftSkillCourse result = softSkillCourseService.addCareerPathsToSoftSkillCourse(courseId, careerPathIds);

        // Assert
        assertEquals(existingCourse, result);
        assertEquals(careerPathIds.size(), existingCourse.getCareerPaths().size());
        assertTrue(existingCourse.getCareerPaths().containsAll(careerPaths));

        verify(springSoftSkillCourseRepository, times(1)).save(existingCourse);
        verify(careerPathService, times(careerPathIds.size())).getCareerPathById(anyLong());
        verify(springSoftSkillCourseRepository, times(1)).findById(courseId);
    }

}