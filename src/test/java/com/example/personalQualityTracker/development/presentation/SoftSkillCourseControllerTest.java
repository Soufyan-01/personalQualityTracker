package com.example.personalQualityTracker.development.presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.personalQualityTracker.development.application.SoftSkillCourseService;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class SoftSkillCourseControllerTest {

    @Mock
    private SoftSkillCourseService softSkillCourseService;

    @InjectMocks
    private SoftSkillCourseController softSkillCourseController;

    @Test
    public void testGetAllSoftSkillCourses() {

        // Prepare test data
        List<SoftSkillCourse> expectedCourses = new ArrayList<>();
        expectedCourses.add(new SoftSkillCourse("Communication Skills", "Improve communication abilities", 2, null));
        expectedCourses.add(new SoftSkillCourse("Communication Skills", "Improve communication abilities", 2, null));

        // Set up mock behavior
        Mockito.when(softSkillCourseService.getAllSoftSkillCourses())
                .thenReturn(expectedCourses);

        // Perform the test
        List<SoftSkillCourse> result = softSkillCourseController.getAllSoftSkillCourses();

        // Verify the result
        assertEquals(expectedCourses, result);
    }

    @Test
    public void testGetSoftSkillCourseByName() {
        // Prepare test data
        String courseName = "Communication Skills";
        Long expectedCourseId = 1L;

        // Set up mock behavior
        Mockito.when(softSkillCourseService.getSoftSkilLCourseByName(Mockito.anyString()))
                .thenReturn(expectedCourseId);

        // Perform the test
        Long result = softSkillCourseController.getSoftSkillCourseByName(courseName);

        // Verify the result
        assertEquals(expectedCourseId, result);
    }

    @Test
    public void testGetSoftSkillCourseById() {
        // Prepare test data
        Long courseId = 1L;
        SoftSkillCourse expectedCourse = new SoftSkillCourse();
        expectedCourse.setId(courseId);

        // Set up mock behavior
        Mockito.when(softSkillCourseService.getSoftSkillCourseBasedOnId(Mockito.anyLong()))
                .thenReturn(expectedCourse);

        // Perform the test
        SoftSkillCourse result = softSkillCourseController.getSoftSkillCourseById(courseId);

        // Verify the result
        assertEquals(expectedCourse, result);
    }


}