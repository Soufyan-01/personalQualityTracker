package com.example.personalQualityTracker.development.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InterestPercentageTest {

    @Test
    public void testConstructorAndGetterMethods() {
        // Create a new InterestPercentage object
        InterestPercentage interestPercentage = new InterestPercentage("Interest 1", 75.5);

        // Verify the name
        assertEquals("Interest 1", interestPercentage.getName());

        // Verify the percentage
        assertEquals(75.5, interestPercentage.getPercentage(), 0.001);
    }
}