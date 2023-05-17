package com.example.personalQualityTracker.development.presentation.DTO;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CareerPathDTOTest {

    @Test
    public void testCareerPathDTOFields() {
        // Create an instance of CareerPathDTO
        CareerPathDTO careerPathDTO = new CareerPathDTO();

        // Create sample interests
        Interest interest1 = Interest.CONVENTIONAL;
        Interest interest2 = Interest.CONVENTIONAL;
        Interest interest3 = Interest.CONVENTIONAL;

        // Create a list of interests
        List<Interest> interestList = new ArrayList<>();
        interestList.add(interest1);
        interestList.add(interest2);
        interestList.add(interest3);

        // Set the values in the CareerPathDTO
        careerPathDTO.id = 1L;
        careerPathDTO.name = "Software Development";
        careerPathDTO.description = "Learn software development skills";
        careerPathDTO.interestList = interestList;

        // Verify that the fields in the CareerPathDTO are correctly set
        assertEquals(1L, careerPathDTO.id);
        assertEquals("Software Development", careerPathDTO.name);
        assertEquals("Learn software development skills", careerPathDTO.description);
        assertEquals(interestList, careerPathDTO.interestList);
    }
}