package com.example.personalQualityTracker.development.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.personalQualityTracker.development.data.SpringAssessmentRepository;
import com.example.personalQualityTracker.development.domain.*;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.example.personalQualityTracker.development.domain.Enum.question.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.persistence.EntityNotFoundException;
import java.util.*;

class AssessmentServiceTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private SpringAssessmentRepository springAssessmentRepository;

    @InjectMocks
    private AssessmentService assessmentService;

    @Captor
    private ArgumentCaptor<Assessment> assessmentCaptor;

    @Mock
    private HardSkillCourseService hardSkillCourseService;

    @Mock
    private SoftSkillCourseService softSkillCourseService;

    @Mock
    private CareerPathService careerPathService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void createNewAssessment_AssessmentNotMade_ReturnsNewAssessment() {
        // Arrange
        Long id = 1L;
        QuestionOne questionOne = QuestionOne.ONE;
        QuestionTwo questionTwo = QuestionTwo.TWO;
        QuestionThree questionThree = QuestionThree.THREE;
        QuestionFour questionFour = QuestionFour.ONE;
        QuestionFive questionFive = QuestionFive.ONE;
        QuestionSix questionSix = QuestionSix.TWO;
        QuestionSeven questionSeven = QuestionSeven.ONE;
        QuestionEight questionEight = QuestionEight.ONE;

        Employee employee = new Employee();
        employee.setId(id);

        when(employeeService.getEmployeeById(id)).thenReturn(employee);
        when(springAssessmentRepository.findAssessmentByPersonId(id)).thenReturn(Optional.empty());
        when(springAssessmentRepository.save(any(Assessment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // Act
        Assessment result = assessmentService.createNewAssessment(id, questionOne, questionTwo, questionThree, questionFour, questionFive, questionSix, questionSeven, questionEight);

        // Assert
        assertNotNull(result); // Assert that the result is not null
        assertEquals(questionOne, result.getQuestionOne()); // Assert that the questionOne matches
        assertEquals(questionTwo, result.getQuestionTwo()); // Assert that the questionTwo matches
        assertEquals(questionThree, result.getQuestionThree()); // Assert that the questionThree matches
        assertEquals(questionFour, result.getQuestionFour()); // Assert that the questionFour matches
        assertEquals(questionFive, result.getQuestionFive()); // Assert that the questionFive matches
        assertEquals(questionSix, result.getQuestionSix()); // Assert that the questionSix matches
        assertEquals(questionSeven, result.getQuestionSeven()); // Assert that the questionSeven matches
        assertEquals(questionEight, result.getQuestionEight()); // Assert that the questionEight matches
        assertEquals(employee, result.getPerson()); // Assert that the person matches
        verify(employeeService, times(1)).getEmployeeById(id);
        verify(springAssessmentRepository, times(1)).findAssessmentByPersonId(id);
        verify(springAssessmentRepository, times(1)).save(any(Assessment.class));
    }


    @Test
    public void getPersonality_AssessmentNotMade_ReturnsAssessmentWithPersonality() {
        // Arrange
        Long id = 1L;
        Assessment existingAssessment = new Assessment();
        existingAssessment.setInterestOne(null);
        existingAssessment.setQuestionOne(QuestionOne.ONE);
        existingAssessment.setQuestionTwo(QuestionTwo.TWO);
        existingAssessment.setQuestionThree(QuestionThree.THREE);
        existingAssessment.setQuestionFour(QuestionFour.ONE);
        existingAssessment.setQuestionFive(QuestionFive.ONE);
        existingAssessment.setQuestionSix(QuestionSix.TWO);
        existingAssessment.setQuestionSeven(QuestionSeven.ONE);
        existingAssessment.setQuestionEight(QuestionEight.ONE);

        Employee person = new Employee();
        person.setId(id);

        when(springAssessmentRepository.findAssessmentByPersonId(id)).thenReturn(Optional.of(existingAssessment));
        when(employeeService.getEmployeeById(id)).thenReturn( person);
        when(springAssessmentRepository.save(any(Assessment.class))).thenReturn(existingAssessment);

        // Act
        Assessment result = assessmentService.getPersonality(id);

        // Assert
        assertNull(result.getInterestOne()); // Assert that the interestOne is set to null
        verify(springAssessmentRepository, times(1)).findAssessmentByPersonId(id);
        verify(employeeService, times(1)).getEmployeeById(id);
        verify(springAssessmentRepository, times(1)).delete(existingAssessment);
    }


    @Test
    public void addCareerPathToAssessment_ValidIds_ReturnsUpdatedAssessment() {
        // Arrange
        Long employeeId = 1L;
        Long careerPathId = 2L;

        Assessment assessment = new Assessment();
        CareerPath careerPath = new CareerPath();

        when(springAssessmentRepository.findAssessmentByPersonId(employeeId)).thenReturn(Optional.of(assessment));
        when(careerPathService.getCareerPathById(careerPathId)).thenReturn(Optional.of(careerPath));
        when(springAssessmentRepository.save(assessment)).thenReturn(assessment);

        // Act
        Assessment result = assessmentService.addCareerPathToAssessment(employeeId, careerPathId);

        // Assert
        assertEquals(careerPath, result.getCareerPath());
        verify(springAssessmentRepository, times(1)).findAssessmentByPersonId(employeeId);
        verify(careerPathService, times(1)).getCareerPathById(careerPathId);
        verify(springAssessmentRepository, times(1)).save(assessment);
    }


    @Test
    public void checkIfAssessmentIsMade_AssessmentDoesNotExist_ReturnsFalse() {
        // Arrange
        Long id = 1L;

        when(springAssessmentRepository.findAssessmentByPersonId(id)).thenReturn(Optional.empty());

        // Act
        boolean result = assessmentService.checkIfAssessmentIsMade(id);

        // Assert
        assertFalse(result);
        verify(springAssessmentRepository, times(1)).findAssessmentByPersonId(id);
    }

    @Test
    public void findMatchHardSkill_AssessmentFound_ReturnsMatchedHardSkills() {
        // Arrange
        Long id = 1L;

        Assessment assessment = new Assessment();
        assessment.setCareerPath(new CareerPath());
        // Create a dummy assessment object and set the necessary properties

        List<HardSkillCourse> hardSkillCourses = new ArrayList<>();
        // Create a list of hard skill courses

        Map<String, Integer> expectedMatchedHardSkills = new HashMap<>();
        // Create a map of expected matched hard skills

        when(springAssessmentRepository.findAssessmentByPersonId(id)).thenReturn(Optional.of(assessment));
        // Mock the behavior of the springAssessmentRepository to return the dummy assessment object

        when(hardSkillCourseService.getAllHardSkillCourses()).thenReturn(hardSkillCourses);
        // Mock the behavior of the hardSkillCourseService to return the list of hard skill courses

        // Act
        Map<String, Integer> result = assessmentService.findMatchHardSkill(id);

        // Assert
        assertEquals(expectedMatchedHardSkills, result);
        verify(springAssessmentRepository, times(1)).findAssessmentByPersonId(id);
        verify(hardSkillCourseService, times(1)).getAllHardSkillCourses();
    }

    @Test
    public void findMatchHardSkill_AssessmentNotFound_ThrowsNoSuchElementException() {
        // Arrange
        Long id = 1L;

        when(springAssessmentRepository.findAssessmentByPersonId(id)).thenReturn(Optional.empty());
        // Mock the behavior of the springAssessmentRepository to return an empty Optional

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> assessmentService.findMatchHardSkill(id));
        verify(springAssessmentRepository, times(1)).findAssessmentByPersonId(id);
    }

    @Test
    public void findMatchingCourses_AssessmentFound_ReturnsMatchingCourses() {
        // Arrange
        Long id = 1L;

        Assessment assessment = new Assessment();
        assessment.setCareerPath(new CareerPath());
        assessment.setInterestOne(String.valueOf(Interest.REALISTIC));
        assessment.setInterestTwo(String.valueOf(Interest.REALISTIC));
        assessment.setInterestThree(String.valueOf(Interest.REALISTIC));
        // Create a dummy assessment object and set the necessary properties

        List<SoftSkillCourse> softSkillCourses = new ArrayList<>();
        // Create a list of soft skill courses

        Map<String, Integer> expectedMatchingCourses = new HashMap<>();
        // Create a map of expected matching courses

        when(springAssessmentRepository.findAssessmentByPersonId(id)).thenReturn(Optional.of(assessment));
        // Mock the behavior of the springAssessmentRepository to return the dummy assessment object

        when(softSkillCourseService.getAllSoftSkillCourses()).thenReturn(softSkillCourses);
        // Mock the behavior of the softSkillCourseService to return the list of soft skill courses

        // Act
        Map<String, Integer> result = assessmentService.findMatchingCourses(id);

        // Assert
        assertEquals(expectedMatchingCourses, result);
        verify(springAssessmentRepository, times(1)).findAssessmentByPersonId(id);
        verify(softSkillCourseService, times(1)).getAllSoftSkillCourses();
    }

    @Test
    public void findMatchingCourses_AssessmentNotFound_ThrowsNoSuchElementException() {
        // Arrange
        Long id = 1L;

        when(springAssessmentRepository.findAssessmentByPersonId(id)).thenReturn(Optional.empty());
        // Mock the behavior of the springAssessmentRepository to return an empty Optional

        // Act and Assert
        assertThrows(NoSuchElementException.class, () -> assessmentService.findMatchingCourses(id));
        verify(springAssessmentRepository, times(1)).findAssessmentByPersonId(id);
    }
}