package com.example.personalQualityTracker.security.presentation.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Registration {
    @NotBlank
    public String username;

    @Size(min = 5)
    public String password;

    @NotBlank
    public String positions;
}
