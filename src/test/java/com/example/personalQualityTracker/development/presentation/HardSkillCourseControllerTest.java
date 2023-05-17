package com.example.personalQualityTracker.development.presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.personalQualityTracker.development.application.HardSkillCourseService;
import com.example.personalQualityTracker.development.domain.HardSkillCourse;
import com.example.personalQualityTracker.development.presentation.DTO.HardSkillCourseDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class HardSkillCourseControllerTest {

    @Mock
    private HardSkillCourseService hardSkillCourseService;

    @InjectMocks
    private HardSkillCourseController hardSkillCourseController;

    @Test
    public void testGetHardSkillCourseById() {
        // Prepare test data
        Long courseId = 1L;
        HardSkillCourse expectedCourse = new HardSkillCourse();
        expectedCourse.setId(courseId);

        // Set up mock behavior
        Mockito.when(hardSkillCourseService.getHardSkillCourseBasedOnId(Mockito.anyLong()))
                .thenReturn(expectedCourse);

        // Perform the test
        HardSkillCourse result = hardSkillCourseController.getSoftHardCourseById(courseId);

        // Verify the result
        assertEquals(expectedCourse, result);
    }

    @Test
    public void testGetHardSkillCourseByName() {
        // Prepare test data
        String courseName = "Java Programming";
        Long expectedCourseId = 1L;

        // Set up mock behavior
        Mockito.when(hardSkillCourseService.getHardSkilLCourseByName(Mockito.anyString()))
                .thenReturn(expectedCourseId);

        // Perform the test
        Long result = hardSkillCourseController.getHardSkillCourseByName(courseName);

        // Verify the result
        assertEquals(expectedCourseId, result);
    }

    @Test
    public void testGetAllHardSkillCourses() {
        // Prepare test data
        List<HardSkillCourse> expectedCourses = new ArrayList<>();
        expectedCourses.add(new HardSkillCourse( "Java Programming", "Learn Java programming language", 1));
        expectedCourses.add(new HardSkillCourse( "Python Programming", "Learn Python programming language", 1));

        // Set up mock behavior
        Mockito.when(hardSkillCourseService.getAllHardSkillCourses())
                .thenReturn(expectedCourses);

        // Perform the test
        List<HardSkillCourse> result = hardSkillCourseController.getAllHardSkillCourses();

        // Verify the result
        assertEquals(expectedCourses, result);
    }


}