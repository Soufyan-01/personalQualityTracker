package com.example.personalQualityTracker.security.presentation.dto;

import com.example.personalQualityTracker.security.data.Positions;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Registration {
    @NotBlank
    public String username;

    @Size(min = 5)
    public String password;

    @NotBlank
    public String positions;

    public Registration(String username, String password, String positions) {
        this.username = username;
        this.password = password;
        this.positions = positions;
    }
}
