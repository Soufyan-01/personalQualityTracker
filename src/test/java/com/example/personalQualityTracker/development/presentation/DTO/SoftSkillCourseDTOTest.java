package com.example.personalQualityTracker.development.presentation.DTO;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SoftSkillCourseDTOTest {


    @Test
    public void testSoftSkillCourseDTOFields() {
        // Create an instance of SoftSkillCourseDTO
        SoftSkillCourseDTO softSkillCourseDTO = new SoftSkillCourseDTO();

        // Create sample interests
        Interest interest1 = Interest.REALISTIC;
        Interest interest2 = Interest.INVESTIGATIVE;
        Interest interest3 = Interest.SOCIAL;

        // Create a list of interests
        List<Interest> interestList = new ArrayList<>();
        interestList.add(interest1);
        interestList.add(interest2);
        interestList.add(interest3);

        // Create a list of career path IDs
        List<Long> careerPath = new ArrayList<>();
        careerPath.add(1L);
        careerPath.add(2L);

        // Set the values in the SoftSkillCourseDTO
        softSkillCourseDTO.courseName = "Effective Communication";
        softSkillCourseDTO.courseDescription = "Improve your communication skills";
        softSkillCourseDTO.courseLevel = 2;
        softSkillCourseDTO.interestList = interestList;
        softSkillCourseDTO.careerPath = careerPath;

        // Verify that the fields in the SoftSkillCourseDTO are correctly set
        assertEquals("Effective Communication", softSkillCourseDTO.courseName);
        assertEquals("Improve your communication skills", softSkillCourseDTO.courseDescription);
        assertEquals(2, softSkillCourseDTO.courseLevel);
        assertEquals(interestList, softSkillCourseDTO.interestList);
        assertEquals(careerPath, softSkillCourseDTO.careerPath);
    }
}