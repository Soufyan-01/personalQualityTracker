package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HardSkillCourseTest {

    private HardSkillCourse hardSkillCourse;
    private CareerPath careerPath1;
    private CareerPath careerPath2;

    @BeforeEach
    void setUp() {
        // Create test data
        String courseName = "Course A";
        String courseDescription = "This course is about A";
        int courseLevel = 1;

        hardSkillCourse = new HardSkillCourse(courseName, courseDescription, courseLevel);

        // Create career paths
        careerPath1 = new CareerPath("Career Path 1", "Uitleg over Career Path 1",  Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE));
        careerPath2 = new CareerPath("Career Path 2", "Uitleg over Career Path 2",  Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE));
    }


    @Test
    public void testAddCareerPathToHardSkillCourse() {
        // Create a new HardSkillCourse
        HardSkillCourse course = new HardSkillCourse("Java Programming", "Learn Java programming", 1);

        // Initialize the careerPaths list
        course.setCareerPaths(new ArrayList<>());

        // Create a CareerPath
        CareerPath careerPath = new CareerPath("Career Path 1", "Uitleg over Career Path 1", Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE));

        // Add the CareerPath to the HardSkillCourse
        course.addCareerPathToHardSkillCourse(careerPath);

        // Retrieve the careerPaths list from the HardSkillCourse
        List<CareerPath> careerPaths = course.getCareerPaths();

        // Verify that the careerPaths list contains the added CareerPath
        assertTrue(careerPaths.contains(careerPath));
    }

    @Test
    public void testDefaultConstructorAndGetterMethods() {
        // Create a new HardSkillCourse using the default constructor
        HardSkillCourse course = new HardSkillCourse();

        // Verify that the id is null
        assertNull(course.getId());

        // Verify that the courseName is null
        assertNull(course.getCourseName());

        // Verify that the courseDescription is null
        assertNull(course.getCourseDescription());

        // Verify that the courseLevel is 0
        assertEquals(0, course.getCourseLevel());
    }




        }