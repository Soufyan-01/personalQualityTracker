package com.example.personalQualityTracker.development.presentation;

import com.example.personalQualityTracker.development.application.AssessmentService;
import com.example.personalQualityTracker.development.domain.Assessment;
import com.example.personalQualityTracker.development.domain.Employee;
import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import com.example.personalQualityTracker.development.presentation.DTO.AssessmentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/assessment")
@CrossOrigin("http://localhost:3000/")
public class AssessmentController {

    private final AssessmentService assessmentService;

    public AssessmentController(AssessmentService assessmentService) {
        this.assessmentService = assessmentService;
    }

    @PostMapping("/assessment/{employeeId}")
    public Assessment newAssessment(@PathVariable Long employeeId, @RequestBody AssessmentDTO assessmentDTO) throws IOException {
        return assessmentService.createNewAssessment(employeeId, assessmentDTO.questionOne, assessmentDTO.questionTwo, assessmentDTO.questionThree, assessmentDTO.questionFour, assessmentDTO.questionFive, assessmentDTO.questionSix, assessmentDTO.questionSeven, assessmentDTO.questionEight);
    }

    @GetMapping("/assessments/{personId}")
    public Assessment getAssessmentByPersonId(@PathVariable Long personId){
        return assessmentService.getAssessmentByPersonId(personId);
    }

    @GetMapping("/doesExists/{personId}")
    public boolean checkIfAssessmentIsMade(@PathVariable Long personId){
        return assessmentService.checkIfAssessmentIsMade(personId);
    }
    @PostMapping("/percentages/{personId}")
    public Assessment getPersonality(@PathVariable Long personId){
        return assessmentService.getPersonality(personId);
    }

    @PostMapping("/addCareerPathToAssessment/{employeeId}/{careerPathId}")
    public ResponseEntity<Assessment> addCareerPathToEmployee(@PathVariable Long employeeId, @PathVariable Long careerPathId) {
        Assessment assessment = assessmentService.addCareerPathToAssessment(employeeId, careerPathId);

        return ResponseEntity.ok(assessment);
    }

    @GetMapping("/matchingcourses/{id}")
    public Map<String, Integer> getMatchingCourses(@PathVariable Long id) {
        return assessmentService.findMatchingCourses(id);
    }
}
