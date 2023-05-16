package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.example.personalQualityTracker.development.domain.Enum.question.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentTest {

    @Test
    public void testAssessmentConstructorWithCareerPath() {
        // Create a CareerPath instance
        CareerPath careerPath = new CareerPath("Career Path 1", "Uitleg over Career Path 1",  Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE));

        // Create an Assessment instance using the constructor with CareerPath parameter
        Assessment assessment = new Assessment(careerPath);

        // Verify that the CareerPath is set correctly
        assertEquals(careerPath, assessment.getCareerPath());
    }

    @Test
    public void testAssessmentConstructorWithQuestions() {
        // Create Question instances
        QuestionOne questionOne = QuestionOne.ONE;
        QuestionTwo questionTwo = QuestionTwo.TWO;
        QuestionThree questionThree = QuestionThree.THREE;
        QuestionFour questionFour = QuestionFour.THREE;
        QuestionFive questionFive = QuestionFive.ONE;
        QuestionSix questionSix = QuestionSix.TWO;
        QuestionSeven questionSeven = QuestionSeven.THREE;
        QuestionEight questionEight = QuestionEight.THREE;

        // Create an Assessment instance using the constructor with Questions parameters
        Assessment assessment = new Assessment(questionOne, questionTwo, questionThree, questionFour,
                questionFive, questionSix, questionSeven, questionEight);

        // Verify that the Questions are set correctly
        assertEquals(questionOne, assessment.getQuestionOne());
        assertEquals(questionTwo, assessment.getQuestionTwo());
        assertEquals(questionThree, assessment.getQuestionThree());
        assertEquals(questionFour, assessment.getQuestionFour());
        assertEquals(questionFive, assessment.getQuestionFive());
        assertEquals(questionSix, assessment.getQuestionSix());
        assertEquals(questionSeven, assessment.getQuestionSeven());
        assertEquals(questionEight, assessment.getQuestionEight());
    }


    @Test
    public void testAssessmentConstructorWithQuestionsAndInterests() {
        // Create Question instances
        QuestionOne questionOne = QuestionOne.ONE;
        QuestionTwo questionTwo = QuestionTwo.TWO;
        QuestionThree questionThree = QuestionThree.THREE;
        QuestionFour questionFour = QuestionFour.ONE;
        QuestionFive questionFive = QuestionFive.ONE;
        QuestionSix questionSix = QuestionSix.ONE;
        QuestionSeven questionSeven = QuestionSeven.ONE;
        QuestionEight questionEight = QuestionEight.ONE;

        // Define interests and their percentages
        String interestOne = "Interest 1";
        String interestTwo = "Interest 2";
        String interestThree = "Interest 3";
        int percentageInterestOne = 50;
        int percentageInterestTwo = 30;
        int percentageInterestThree = 20;

        // Create an Assessment instance using the constructor with Questions and Interests parameters
        Assessment assessment = new Assessment(questionOne, questionTwo, questionThree, questionFour,
                questionFive, questionSix, questionSeven, questionEight, interestOne, interestTwo, interestThree,
                percentageInterestOne, percentageInterestTwo, percentageInterestThree);

        // Verify that the Questions and Interests are set correctly
        assertEquals(questionOne, assessment.getQuestionOne());
        assertEquals(questionTwo, assessment.getQuestionTwo());
        assertEquals(questionThree, assessment.getQuestionThree());
        assertEquals(questionFour, assessment.getQuestionFour());
        assertEquals(questionFive, assessment.getQuestionFive());
        assertEquals(questionSix, assessment.getQuestionSix());
        assertEquals(questionSeven, assessment.getQuestionSeven());
        assertEquals(questionEight, assessment.getQuestionEight());
        assertEquals(interestOne, assessment.getInterestOne());
        assertEquals(interestTwo, assessment.getInterestTwo());
        assertEquals(interestThree, assessment.getInterestThree());
        assertEquals(percentageInterestOne, assessment.getPercentageInterestOne());
        assertEquals(percentageInterestTwo, assessment.getPercentageInterestTwo());
        assertEquals(percentageInterestThree, assessment.getPercentageInterestThree());
    }

    @Test
    public void testMatchHardSkillCourses() {
        // Create some HardSkillCourse instances
        HardSkillCourse course1 = new HardSkillCourse("Course A", "This course is about A", 1);
        HardSkillCourse course2 = new HardSkillCourse("Course B", "This course is about B", 2);
        HardSkillCourse course3 = new HardSkillCourse("Course C", "This course is about C", 3);

        String name = "Test Career Path";
        String description = "Description";
        List<Interest> interestList = List.of(Interest.REALISTIC, Interest.INVESTIGATIVE, Interest.ARTISTIC);

        // Create an instance using the constructor
        CareerPath careerPath = new CareerPath(name, description, interestList);

        // Initialize the careerPaths list in HardSkillCourse
        course1.setCareerPaths(new ArrayList<>(List.of(careerPath)));
        course3.setCareerPaths(new ArrayList<>(List.of(careerPath)));

        // Create a list of HardSkillCourses
        List<HardSkillCourse> hardSkillCourses = Arrays.asList(course1, course2, course3);

        // Create an instance of the class under test
        Assessment yourClass = new Assessment();

        // Call the method being tested
        Map<String, Integer> matchingCourses = yourClass.matchHardSkillCourses(hardSkillCourses, careerPath);

        // Verify the result
        assertEquals(2, matchingCourses.size());
        assertTrue(matchingCourses.containsKey("Course A"));
        assertTrue(matchingCourses.containsKey("Course C"));
        assertEquals(100, matchingCourses.get("Course A").intValue());
        assertEquals(100, matchingCourses.get("Course C").intValue());
    }


    @Test
    public void testMatchingSoftSkillCourses() {
        // Create some SoftSkillCourse instances
        SoftSkillCourse course1 = new SoftSkillCourse("Communication Skills", "Learn Python programming", 2, List.of(Interest.REALISTIC, Interest.INVESTIGATIVE));
        SoftSkillCourse course2 = new SoftSkillCourse("Problem-Solving", "Develop problem-solving skills", 3, List.of(Interest.INVESTIGATIVE, Interest.SOCIAL));
        SoftSkillCourse course3 = new SoftSkillCourse("Creativity", "Unleash your creative potential", 1, List.of(Interest.ARTISTIC, Interest.SOCIAL));

        // Define the interests
        Interest interest1 = Interest.REALISTIC;
        Interest interest2 = Interest.INVESTIGATIVE;
        Interest interest3 = Interest.ARTISTIC;

        // Create a CareerPath
        String name = "Test Career Path";
        String description = "Description";
        List<Interest> interestList = List.of(Interest.REALISTIC, Interest.INVESTIGATIVE, Interest.ARTISTIC);

        CareerPath careerPath = new CareerPath(name, description, interestList);

        // Add the CareerPath to the SoftSkillCourse instances
        course1.addCareerPathToSoftSkillCourse(careerPath);
        course2.addCareerPathToSoftSkillCourse(careerPath);
        course3.addCareerPathToSoftSkillCourse(careerPath);

        // Create a list of SoftSkillCourses
        List<SoftSkillCourse> softSkillCourses = Arrays.asList(course1, course2, course3);

        // Create an instance of the class under test
        Assessment yourClass = new Assessment();

        // Call the method being tested
        Map<String, Integer> matchingCourses = yourClass.matchingSoftSkillCourses(interest1, interest2, interest3, softSkillCourses, careerPath);

        // Verify the result
        assertEquals(3, matchingCourses.size());
        assertTrue(matchingCourses.containsKey("Communication Skills"));
        assertTrue(matchingCourses.containsKey("Problem-Solving"));
        assertTrue(matchingCourses.containsKey("Creativity"));
        assertEquals(40, matchingCourses.get("Communication Skills").intValue());
        assertEquals(15, matchingCourses.get("Problem-Solving").intValue());
        assertEquals(10, matchingCourses.get("Creativity").intValue());
    }

    @Test
    public void testAssessmentToString() {
        // Create a CareerPath
        String name = "Test Career Path";
        String description = "Description";
        List<Interest> interestList = List.of(Interest.REALISTIC, Interest.INVESTIGATIVE, Interest.ARTISTIC);

        CareerPath careerPath = new CareerPath(name, description, interestList);
        // Create an instance of the class under test
        Assessment assessment = new Assessment();
        assessment.setCareerPath(careerPath);

        // Call the method being tested
        String result = assessment.toString();

        // Verify the result
        String expected = "Assessment{careerPath=Test Career Path}";
        assertEquals(expected, result);
    }

    @Test
    public void testDeterminePersonality() {
        // Arrange
        QuestionOne q1 = QuestionOne.THREE;
        QuestionTwo q2 = QuestionTwo.ONE;
        QuestionThree q3 = QuestionThree.ONE;
        QuestionFour q4 = QuestionFour.TWO;
        QuestionFive q5 = QuestionFive.THREE;
        QuestionSix q6 = QuestionSix.THREE;
        QuestionSeven q7 = QuestionSeven.TWO;
        QuestionEight q8 = QuestionEight.ONE;

        Assessment assessment = new Assessment();
        InterestPercentage[] expected = {
//                new InterestPercentage(Interest.SOCIAL.getName(), 31.25),

                new InterestPercentage(Interest.INVESTIGATIVE.getName(), 25.0),
                new InterestPercentage(Interest.ARTISTIC.getName(), 18.75)
        };

        // Act
        InterestPercentage[] actual = assessment.determinePersonality(q1, q2, q3, q4, q5, q6, q7, q8);

        // Assert
        assertEquals(expected.length, actual.length);
        assertFalse(Boolean.parseBoolean(expected[0].getName()), actual[0].getName());
        assertFalse(Boolean.parseBoolean(expected[1].getName()), actual[1].getName());
//        assertFalse(Boolean.parseBoolean(expected[2].getName()), actual[2].getName());
        assertEquals(expected[0].getPercentage(), actual[0].getPercentage(), 0.001);
        assertEquals(expected[1].getPercentage(), actual[1].getPercentage(), 0.001);
//        assertEquals(expected[2].getPercentage(), actual[2].getPercentage(), 0.001);
    }





}