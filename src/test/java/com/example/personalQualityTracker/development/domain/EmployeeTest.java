package com.example.personalQualityTracker.development.domain;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeTest {

    Employee emailValidation = new Employee();

    @Test
    public void testValidEmail() {
        boolean result = emailValidation.isValid("example@gmail.com");
        assertTrue(result);
    }

    @Test
    public void testInvalidEmail() {
        boolean result = emailValidation.isValid("example@");
        assertFalse(result);
    }

    @Test
    public void testNullInput() {
        boolean result = emailValidation.isValid(null);
        assertFalse(result);
    }

    @Test
    public void testTwoLetterTLD() {
        boolean result = emailValidation.isValid("example@yahoo.ca");
        assertTrue(result);
    }
}