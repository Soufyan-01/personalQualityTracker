package com.example.personalQualityTracker.development.presentation;

import com.example.personalQualityTracker.development.application.SoftSkillCourseService;
import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import com.example.personalQualityTracker.development.presentation.DTO.SoftSkillCourseDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/softskillcourse")
@CrossOrigin("http://localhost:3000/")
public class SoftSkillCourseController {

    private final SoftSkillCourseService softSkillCourseService;

    public SoftSkillCourseController(SoftSkillCourseService softSkillCourseService) {
        this.softSkillCourseService = softSkillCourseService;
    }

    @PostMapping("/newCourse")
    public SoftSkillCourse newSoftSkillCourse(@RequestBody SoftSkillCourseDTO softSkillCourseDTO){
        return softSkillCourseService.createNewSoftSkill(softSkillCourseDTO.courseName,
                softSkillCourseDTO.courseDescription, softSkillCourseDTO.courseLevel,
                softSkillCourseDTO.interestList);
    }

    @PostMapping("/finishCourseOption/{courseId}")
    public SoftSkillCourse addCareerPathsToSoftSkillCourse(@PathVariable Long courseId, @RequestBody SoftSkillCourseDTO softSkillCourseDTO) {
                return softSkillCourseService.addCareerPathsToSoftSkillCourse(courseId, softSkillCourseDTO.careerPath);
    }

    @GetMapping("/allCourses")
    public List<SoftSkillCourse> getAllSoftSkillCourses(){
        return softSkillCourseService.getAllSoftSkillCourses();
    }

    @GetMapping("/softskillcourses/{id}")
    public SoftSkillCourse getSoftSkillCourseById(@PathVariable Long id) {
        return softSkillCourseService.getSoftSkillCourseBasedOnId(id);
    }

    @GetMapping("/softskillcourseByName/{name}")
    public Long getSoftSkillCourseByName(@PathVariable String name) {
        return softSkillCourseService.getSoftSkilLCourseByName(name);
    }





}
