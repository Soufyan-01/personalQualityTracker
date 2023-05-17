package com.example.personalQualityTracker.development.presentation.DTO;

import com.example.personalQualityTracker.development.domain.Enum.question.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AssessmentDTOTest {

    @Test
    public void testAssessmentDTOFields() {
        // Create an instance of AssessmentDTO
        AssessmentDTO assessmentDTO = new AssessmentDTO();

        // Create sample Question objects
        QuestionOne questionOne = QuestionOne.ONE;
        QuestionTwo questionTwo = QuestionTwo.TWO;
        QuestionThree questionThree = QuestionThree.THREE;
        QuestionFour questionFour = QuestionFour.ONE;
        QuestionFive questionFive = QuestionFive.ONE;
        QuestionSix questionSix = QuestionSix.TWO;
        QuestionSeven questionSeven = QuestionSeven.ONE;
        QuestionEight questionEight = QuestionEight.ONE;

        // Set the sample Question objects in the AssessmentDTO
        assessmentDTO.questionOne = questionOne;
        assessmentDTO.questionTwo = questionTwo;
        assessmentDTO.questionThree = questionThree;
        assessmentDTO.questionFour = questionFour;
        assessmentDTO.questionFive = questionFive;
        assessmentDTO.questionSix = questionSix;
        assessmentDTO.questionSeven = questionSeven;
        assessmentDTO.questionEight = questionEight;

        // Verify that the fields in the AssessmentDTO are correctly set
        assertEquals(questionOne, assessmentDTO.questionOne);
        assertEquals(questionTwo, assessmentDTO.questionTwo);
        assertEquals(questionThree, assessmentDTO.questionThree);
        assertEquals(questionFour, assessmentDTO.questionFour);
        assertEquals(questionFive, assessmentDTO.questionFive);
        assertEquals(questionSix, assessmentDTO.questionSix);
        assertEquals(questionSeven, assessmentDTO.questionSeven);
        assertEquals(questionEight, assessmentDTO.questionEight);
    }

}