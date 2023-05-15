package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamLeadTest {

        @Test
        public void testStreamLeadConstructorAndGetters() {
            // Create a stream lead
            StreamLead streamLead = new StreamLead("John", "Doe", "johndoe", "john@example.com", Function.EMPLOYEE);

            // Verify the inherited properties from the Person class
            assertEquals("John", streamLead.getName());
            assertEquals("Doe", streamLead.getSurname());
            assertEquals("johndoe", streamLead.getLoginEmail());

            // Verify the email property
            assertEquals("john@example.com", streamLead.getEmail());

            // Verify the function property
            assertEquals(Function.EMPLOYEE, streamLead.getFunction());
        }

        @Test
        public void testIsValidWithValidEmail() {
            StreamLead streamLead = new StreamLead();

            // Verify a valid email
            assertTrue(streamLead.isValid("john@example.com"));
        }

        @Test
        public void testIsValidWithInvalidEmail() {
            StreamLead streamLead = new StreamLead();

            // Verify an invalid email
            assertFalse(streamLead.isValid("invalid_email"));
        }


    @Test
    public void testIsValid_ValidEmail_ReturnsTrue() {
        // Create a StreamLead instance
        StreamLead streamLead = new StreamLead();

        // Test a valid email address
        String validEmail = "test@example.com";
        boolean isValid = streamLead.isValid(validEmail);

        // Verify that the email is valid
        assertTrue(isValid);
    }

    @Test
    public void testIsValid_NullEmail_ReturnsFalse() {
        // Create a StreamLead instance
        StreamLead streamLead = new StreamLead();

        // Test a null email address
        String nullEmail = null;
        boolean isValid = streamLead.isValid(nullEmail);

        // Verify that the email is invalid
        assertFalse(isValid);
    }

    @Test
    public void testIsValid_InvalidEmail_ReturnsFalse() {
        // Create a StreamLead instance
        StreamLead streamLead = new StreamLead();

        // Test an invalid email address
        String invalidEmail = "invalid_email";
        boolean isValid = streamLead.isValid(invalidEmail);

        // Verify that the email is invalid
        assertFalse(isValid);
    }

    @Test
    public void testStreamLeadId() {
        // Create a StreamLead instance
        StreamLead streamLead = new StreamLead();

        // Set a value for the id
        Long id = 1L;
        streamLead.setId(id);

        // Get the id value from the StreamLead
        Long retrievedId = streamLead.getId();

        // Verify that the retrieved id matches the set id
        assertEquals(id, retrievedId);
    }






        }