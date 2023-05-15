package com.example.personalQualityTracker.development.presentation.DTO;

import com.example.personalQualityTracker.development.domain.Enum.Interest;

import java.util.List;

public class SoftSkillCourseDTO {

    public String courseName;
    public String courseDescription;

    public int courseLevel;

    public List<Interest> interestList;

    public List<Long> careerPath;
}
