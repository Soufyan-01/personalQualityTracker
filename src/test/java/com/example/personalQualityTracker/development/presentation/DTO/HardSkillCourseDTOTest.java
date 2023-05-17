package com.example.personalQualityTracker.development.presentation.DTO;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HardSkillCourseDTOTest {


    @Test
    public void testHardSkillCourseDTOFields() {
        // Create an instance of HardSkillCourseDTO
        HardSkillCourseDTO hardSkillCourseDTO = new HardSkillCourseDTO();

        // Create sample interests
        Interest interest1 = Interest.REALISTIC;
        Interest interest2 = Interest.REALISTIC;
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

        // Set the values in the HardSkillCourseDTO
        hardSkillCourseDTO.courseName = "Java Programming";
        hardSkillCourseDTO.courseDescription = "Learn Java programming skills";
        hardSkillCourseDTO.courseLevel = 3;
        hardSkillCourseDTO.interestList = interestList;
        hardSkillCourseDTO.careerPath = careerPath;

        // Verify that the fields in the HardSkillCourseDTO are correctly set
        assertEquals("Java Programming", hardSkillCourseDTO.courseName);
        assertEquals("Learn Java programming skills", hardSkillCourseDTO.courseDescription);
        assertEquals(3, hardSkillCourseDTO.courseLevel);
        assertEquals(interestList, hardSkillCourseDTO.interestList);
        assertEquals(careerPath, hardSkillCourseDTO.careerPath);
    }
}