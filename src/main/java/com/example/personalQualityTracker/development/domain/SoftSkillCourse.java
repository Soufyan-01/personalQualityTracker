package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
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

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection(targetClass = Interest.class)
    private List<Interest> interestList;

    public SoftSkillCourse(String courseName, String courseDescription, int courseLevel, List<Interest> interestList) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseLevel = courseLevel;
        this.interestList = interestList;
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
