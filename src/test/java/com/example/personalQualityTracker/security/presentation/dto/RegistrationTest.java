package com.example.personalQualityTracker.security.presentation.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class RegistrationTest {

    @Test
    public void testValidRegistration() {
        // Arrange
        String username = "JohnDoe";
        String password = "secure123";
        String positions = "Manager";

        // Act
        Registration registration = new Registration(username, password, positions);

        // Assert
        Assertions.assertEquals(username, registration.username);
        Assertions.assertEquals(password, registration.password);
        Assertions.assertEquals(positions, registration.positions);
    }


}