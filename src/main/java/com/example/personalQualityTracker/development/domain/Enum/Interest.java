package com.example.personalQualityTracker.development.domain.Enum;

public enum Interest {
    REALISTIC("Realistic"),
    INVESTIGATIVE("Investigative"),
    ARTISTIC("Artistic"),
    SOCIAL("Social"),
    ENTERPRISING("Enterprising"),
    CONVENTIONAL("Conventional");

    private final String name;

    Interest(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }
}
