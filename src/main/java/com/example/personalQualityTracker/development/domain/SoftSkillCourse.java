package com.example.personalQualityTracker.development.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "SoftSkillCourse")
public class SoftSkillCourse implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String courseName;

    @Column
    private String courseDescription;

    @Column
    private int courseLevel;
    @OneToMany
    private List<CareerPath> careerPaths;

    public SoftSkillCourse(String courseName, String courseDescription, int courseLevel) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseLevel = courseLevel;
    }

    public SoftSkillCourse(List<CareerPath> careerPaths) {
        this.careerPaths = careerPaths;
    }

    public SoftSkillCourse() {
    }

    public void addCareerPathToSoftSkillCourse(CareerPath careerPath) {
        careerPaths.add(careerPath);
    }
}
