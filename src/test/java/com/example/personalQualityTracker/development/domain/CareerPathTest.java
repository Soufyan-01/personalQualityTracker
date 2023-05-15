package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CareerPathTest {

        @Test
        void getId() {
            Long id = 1L;
            CareerPath careerPath = new CareerPath();
            careerPath.setId(id);
            assertEquals(id, careerPath.getId());
        }

        @Test
        void getName() {
            String name = "Test Career Path";
            CareerPath careerPath = new CareerPath();
            careerPath.setName(name);
            assertEquals(name, careerPath.getName());
        }

        @Test
        void getDescription() {
            String description = "Test Career Path Description";
            CareerPath careerPath = new CareerPath();
            careerPath.setDescription(description);
            assertEquals(description, careerPath.getDescription());
        }

        @Test
        void getInterestList() {
            List<Interest> interestList = Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE);
            CareerPath careerPath = new CareerPath();
            careerPath.setInterestList(interestList);
            assertEquals(interestList, careerPath.getInterestList());
        }

        @Test
        void setId() {
            Long id = 1L;
            CareerPath careerPath = new CareerPath();
            careerPath.setId(id);
            assertEquals(id, careerPath.getId());
        }

        @Test
        void setName() {
            String name = "Test Career Path";
            CareerPath careerPath = new CareerPath();
            careerPath.setName(name);
            assertEquals(name, careerPath.getName());
        }

        @Test
        void setDescription() {
            String description = "Test Career Path Description";
            CareerPath careerPath = new CareerPath();
            careerPath.setDescription(description);
            assertEquals(description, careerPath.getDescription());
        }

        @Test
        void setInterestList() {
            List<Interest> interestList = Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE);
            CareerPath careerPath = new CareerPath();
            careerPath.setInterestList(interestList);
            assertEquals(interestList, careerPath.getInterestList());
        }

    @Test
    void careerPathConstructor() {
        // Create sample data
        String name = "Test Career Path";
        String description = "Description";
        List<Interest> interestList = List.of(Interest.REALISTIC, Interest.INVESTIGATIVE, Interest.ARTISTIC);

        // Create an instance using the constructor
        CareerPath careerPath = new CareerPath(name, description, interestList);

        // Assert the instance properties
        assertEquals(name, careerPath.getName());
        assertEquals(description, careerPath.getDescription());
        assertEquals(interestList, careerPath.getInterestList());

        // Add more assertions as per your requirements and expected behavior
    }


}