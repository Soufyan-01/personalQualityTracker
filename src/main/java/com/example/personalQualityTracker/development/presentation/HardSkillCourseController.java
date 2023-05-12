package com.example.personalQualityTracker.development.presentation;

import com.example.personalQualityTracker.development.application.HardSkillCourseService;
import com.example.personalQualityTracker.development.domain.HardSkillCourse;
import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import com.example.personalQualityTracker.development.presentation.DTO.HardSkillCourseDTO;
import com.example.personalQualityTracker.development.presentation.DTO.SoftSkillCourseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hardskillcourse")
@CrossOrigin("http://localhost:3000/")
public class HardSkillCourseController {

    private final HardSkillCourseService hardSkillCourseService;

    public HardSkillCourseController(HardSkillCourseService hardSkillCourseService) {
        this.hardSkillCourseService = hardSkillCourseService;
    }

    @PostMapping("/newCourse")
    public HardSkillCourse newHardSkillCourse(@RequestBody HardSkillCourseDTO hardSkillCourseDTO){
        return hardSkillCourseService.createNewHardSkillCourse(hardSkillCourseDTO.courseName,
                hardSkillCourseDTO.courseDescription, hardSkillCourseDTO.courseLevel);
    }

    @PostMapping("/finishCourseOption/{courseId}")
    public HardSkillCourse addCareerPathsToHardSkillCourse(@PathVariable Long courseId, @RequestBody HardSkillCourseDTO hardSkillCourseDTO) {
        return hardSkillCourseService.addCareerPathsToHardSkillCourse(courseId, hardSkillCourseDTO.careerPath);
    }

    @GetMapping("/allCourses")
    public List<HardSkillCourse> getAllHardSkillCourses(){
        return hardSkillCourseService.getAllHardSkillCourses();
    }

    @GetMapping("/hardskillcourses/{id}")
    public HardSkillCourse getSoftHardCourseById(@PathVariable Long id) {
        return hardSkillCourseService.getHardSkillCourseBasedOnId(id);
    }

}
