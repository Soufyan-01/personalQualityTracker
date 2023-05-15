package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SoftSkillCourseTest {

    @Test
    public void testAddCareerPathToSoftSkillCourse() {
        // Create a new SoftSkillCourse
        SoftSkillCourse course = new SoftSkillCourse("Communication Skills", "Improve communication abilities", 2, null);

        // Create a CareerPath
        CareerPath careerPath = new CareerPath("Career Path 1", "Description of Career Path 1", Arrays.asList(Interest.REALISTIC, Interest.SOCIAL));

        // Initialize the careerPaths list
        course.setCareerPaths(new ArrayList<>());

        // Add the CareerPath to the SoftSkillCourse
        course.addCareerPathToSoftSkillCourse(careerPath);

        // Retrieve the careerPaths list from the SoftSkillCourse
        List<CareerPath> careerPaths = course.getCareerPaths();

        // Verify that the careerPaths list contains the added CareerPath
        assertTrue(careerPaths.contains(careerPath));
    }

    @Test
    public void testConstructorWithCareerPaths() {
        // Create a list of CareerPaths
        List<CareerPath> careerPaths = new ArrayList<>();
        CareerPath careerPath1 = new CareerPath("Career Path 1", "Description of Career Path 1", Arrays.asList(Interest.INVESTIGATIVE, Interest.ARTISTIC));
        CareerPath careerPath2 = new CareerPath("Career Path 2", "Description of Career Path 2", Arrays.asList(Interest.REALISTIC, Interest.ENTERPRISING));
        careerPaths.add(careerPath1);
        careerPaths.add(careerPath2);

        // Create a new SoftSkillCourse using the constructor with careerPaths
        SoftSkillCourse course = new SoftSkillCourse(careerPaths);

        // Retrieve the careerPaths list from the SoftSkillCourse
        List<CareerPath> retrievedCareerPaths = course.getCareerPaths();

        // Verify that the retrieved careerPaths list matches the original list
        assertEquals(careerPaths, retrievedCareerPaths);
    }

    @Test
    public void testCourseConstructorAndGetters() {
        // Create a course
        SoftSkillCourse course = new SoftSkillCourse("Communication Skills", "Improve communication abilities", 2, null);

        // Verify the course properties
        assertEquals("Communication Skills", course.getCourseName());
        assertEquals("Improve communication abilities", course.getCourseDescription());
        assertEquals(2, course.getCourseLevel());
    }

    @Test
    public void testCourseSetters() {
        // Create a course
        SoftSkillCourse course = new SoftSkillCourse("Communication Skills", "Learn Python programming", 2, null);

        // Set the course properties
        course.setCourseName("Learn Python programming");
        course.setCourseDescription("Learn Python programming");
        course.setCourseLevel(2);

        // Verify the course properties
        assertEquals("Learn Python programming", course.getCourseName());
        assertEquals("Learn Python programming", course.getCourseDescription());
        assertEquals(2, course.getCourseLevel());
    }


    @Test
    public void testSoftSkillCourseConstructorAndGetters() {
        // Create a soft skill course
        SoftSkillCourse course = new SoftSkillCourse();

        // Initialize the interestList property as an empty list
        course.setInterestList(new ArrayList<>());

        // Verify the interestList property is initialized as an empty list
        assertNotNull(course.getInterestList());
        assertTrue(course.getInterestList().isEmpty());
    }

    @Test
    public void testSoftSkillCourseSetters() {
        // Create a soft skill course
        SoftSkillCourse course = new SoftSkillCourse();

        // Create a list of interests
        List<Interest> interestList = Arrays.asList(Interest.ARTISTIC, Interest.SOCIAL);

        // Set the interestList property
        course.setInterestList(interestList);

        // Verify the interestList property is set correctly
        assertEquals(interestList, course.getInterestList());
    }

    @Test
    public void testSoftSkillCourseIdSetter() {
        // Create a soft skill course
        SoftSkillCourse course = new SoftSkillCourse();

        // Set the id property
        Long id = 1L;
        course.setId(id);

        // Verify the id property is set correctly
        assertEquals(id, course.getId());
    }

}