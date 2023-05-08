package com.example.personalQualityTracker.development.presentation.DTO;

import com.example.personalQualityTracker.development.domain.Enum.Interest;

import java.util.List;

public class CareerPathDTO {
    public Long id;
    public String name;
    public String description;
    public List<Interest> interestList;
}
