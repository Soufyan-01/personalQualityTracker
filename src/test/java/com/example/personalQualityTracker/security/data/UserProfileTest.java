package com.example.personalQualityTracker.security.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserProfileTest {

    @Test
    public void testUserProfileConstructorAndGetters() {
        // Create a UserProfile instance
        UserProfile userProfile = new UserProfile("johnDoe", "John", "Doe");

        // Verify that the username, firstName, and lastName are correctly set
        assertEquals("johnDoe", userProfile.getUsername());
        assertEquals("John", userProfile.getFirstName());
        assertEquals("Doe", userProfile.getLastName());
    }
}