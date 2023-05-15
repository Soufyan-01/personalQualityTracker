package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringAssessmentRepository;
import com.example.personalQualityTracker.development.domain.*;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.example.personalQualityTracker.development.domain.Enum.question.*;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class AssessmentService {

    private final SpringAssessmentRepository springAssessmentRepository;

    private final EmployeeService employeeService;

    private final SoftSkillCourseService softSkillCourseService;

    private final CareerPathService careerPathService;

    private final HardSkillCourseService hardSkillCourseService;

    public AssessmentService(SpringAssessmentRepository springAssessmentRepository, EmployeeService employeeService, SoftSkillCourseService softSkillCourseService, CareerPathService careerPathService, HardSkillCourseService hardSkillCourseService) {
        this.springAssessmentRepository = springAssessmentRepository;
        this.employeeService = employeeService;
        this.softSkillCourseService = softSkillCourseService;
        this.careerPathService = careerPathService;
        this.hardSkillCourseService = hardSkillCourseService;
    }

    public Assessment createNewAssessment(Long id, QuestionOne questionOne, QuestionTwo questionTwo, QuestionThree questionThree, QuestionFour questionFour, QuestionFive questionFive, QuestionSix questionSix, QuestionSeven questionSeven, QuestionEight questionEight){
        Person person = employeeService.getEmployeeById(id);

        Optional<Assessment> assessmentOptional = springAssessmentRepository.findAssessmentByPersonId(id);
        if (assessmentOptional.isEmpty()) {

            Assessment assessment = new Assessment(questionOne, questionTwo, questionThree, questionFour, questionFive, questionSix, questionSeven, questionEight);
            assessment.setPerson(person);

            assessment.determinePersonality(questionOne, questionTwo, questionThree, questionFour, questionFive, questionSix, questionSeven, questionEight);

            return springAssessmentRepository.save(assessment);
        } else {
            throw new IllegalArgumentException("Assessment is already made");
        }
    }

    public Assessment getAssessmentByPersonId(Long id) {
        return springAssessmentRepository.findAssessmentByPersonId(id).get();
    }

    public boolean checkIfAssessmentIsMade(Long id){
        Optional<Assessment> assessmentOptional = springAssessmentRepository.findAssessmentByPersonId(id);
        return assessmentOptional.isPresent();
    }


    public Assessment getPersonality(Long id){
        Assessment assessment = getAssessmentByPersonId(id);

        Person person = employeeService.getEmployeeById(id);

        if(assessment.getInterestOne() == null) {

            InterestPercentage[] percentages = assessment.determinePersonality(assessment.getQuestionOne(), assessment.getQuestionTwo(), assessment.getQuestionThree(), assessment.getQuestionFour(), assessment.getQuestionFive(), assessment.getQuestionSix(), assessment.getQuestionSeven(), assessment.getQuestionEight());

            Assessment assessmentScore = new Assessment(assessment.getQuestionOne(), assessment.getQuestionTwo(), assessment.getQuestionThree(), assessment.getQuestionFour(), assessment.getQuestionFive(), assessment.getQuestionSix(), assessment.getQuestionSeven(), assessment.getQuestionEight(),
                    percentages[0].getName(), percentages[1].getName(), percentages[2].getName(),
                    (int) percentages[0].getPercentage(), (int) percentages[1].getPercentage(), (int) percentages[2].getPercentage());

            assessmentScore.setPerson(person);

            springAssessmentRepository.delete(assessment);

            return springAssessmentRepository.save(assessmentScore);
        } else {
            throw new IllegalArgumentException("Assessment is already made");
        }
    }


    public Assessment addCareerPathToAssessment(Long employeeId, Long careerPathId) {
        Assessment assessmentCareerPath = getAssessmentByPersonId(employeeId);
        CareerPath careerPath = careerPathService.getCareerPathById(careerPathId)
                .orElseThrow(() -> new EntityNotFoundException("Career path with id " + careerPathId + " not found"));


        assessmentCareerPath.setCareerPath(careerPath);
        return springAssessmentRepository.save(assessmentCareerPath);
    }


    public Map<String, Integer> findMatchingCourses(Long id) {

        Assessment assessment = getAssessmentByPersonId(id);


        List<SoftSkillCourse> softSkillCourses = softSkillCourseService.getAllSoftSkillCourses();


        Interest interestOne = Interest.valueOf(assessment.getInterestOne().toUpperCase());
        Interest interestTwo = Interest.valueOf(assessment.getInterestTwo().toUpperCase());;
        Interest interestThree = Interest.valueOf(assessment.getInterestThree().toUpperCase());;


        return assessment.matchingSoftSkillCourses(
                interestOne, interestTwo, interestThree, softSkillCourses, assessment.getCareerPath()
        );
    }

    public Map<String, Integer> findMatchHardSkill(Long id) {

        Assessment assessment = getAssessmentByPersonId(id);


        List<HardSkillCourse> hardSkillCourses = hardSkillCourseService.getAllHardSkillCourses();

        return assessment.matchHardSkillCourses(
                hardSkillCourses, assessment.getCareerPath()
        );
    }


}
