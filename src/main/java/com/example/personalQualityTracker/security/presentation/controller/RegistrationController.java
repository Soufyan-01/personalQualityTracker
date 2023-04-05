package com.example.personalQualityTracker.security.presentation.controller;

import com.example.personalQualityTracker.security.application.UserService;
import com.example.personalQualityTracker.security.presentation.dto.Registration;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/register")
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void register(@Validated @RequestBody Registration registration, HttpServletResponse response) {
        try{
            this.userService.register(
                    registration.username,
                    registration.password,
                    registration.positions
            );
            response.setStatus(200);
        }catch (Exception ignored){
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
