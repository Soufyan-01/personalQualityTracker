package com.example.personalQualityTracker.development.domain;

public class InterestPercentage {
    private String name;
    private double percentage;

    public InterestPercentage(String name, double percentage) {
        this.name = name;
        this.percentage = percentage;
    }

    public String getName() {
        return name;
    }

    public double getPercentage() {
        return percentage;
    }
}

