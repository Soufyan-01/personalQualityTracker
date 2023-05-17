package com.example.personalQualityTracker.security.presentation.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.*;

class LoginTest {
    @Test
    public void testValidLogin() {
        // Arrange
        String username = "JohnDoe";
        String password = "secure123";

        // Act
        Login login = new Login(username, password);

        // Assert
        Assertions.assertEquals(username, login.username);
        Assertions.assertEquals(password, login.password);
    }


}