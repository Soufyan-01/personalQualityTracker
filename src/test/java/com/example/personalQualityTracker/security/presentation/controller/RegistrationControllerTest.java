package com.example.personalQualityTracker.security.presentation.controller;

import static org.junit.jupiter.api.Assertions.*;

import com.example.personalQualityTracker.security.application.UserService;
import com.example.personalQualityTracker.security.data.Positions;
import com.example.personalQualityTracker.security.presentation.dto.Registration;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import javax.servlet.http.HttpServletResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class RegistrationControllerTest {


    @Test
    public void testRegister_ValidRegistration_SuccessfulRegistration() {
        // Create a mock instance of UserService
        UserService userService = Mockito.mock(UserService.class);

        // Create a mock HttpServletResponse
        MockHttpServletResponse response = new MockHttpServletResponse();

        // Create a valid registration object
        Registration registration = new Registration("username", "password", "positions");


        // Create a mock BindingResult with no errors
        BindingResult bindingResult = new BeanPropertyBindingResult(registration, "registration");

        // Create an instance of the RegistrationController with the mock dependencies
        RegistrationController registrationController = new RegistrationController(userService);

        // Perform the register method
        registrationController.register(registration, response);

        // Verify that the UserService's register method is called with the correct arguments
        verify(userService, times(1)).register("username", "password", "positions");

        // Verify that the response status is set to 200
        assertEquals(HttpServletResponse.SC_OK, response.getStatus());
    }


}