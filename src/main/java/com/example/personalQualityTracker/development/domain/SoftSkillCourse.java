package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "SoftSkillCourse")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "soft_skill_course_career_paths",
            joinColumns = @JoinColumn(name = "soft_skill_course_id"),
            inverseJoinColumns = @JoinColumn(name = "career_paths_id"),
            uniqueConstraints = {},
            indexes = {})
    private List<CareerPath> careerPaths;


    @Enumerated(EnumType.ORDINAL)
    @ElementCollection(targetClass = Interest.class)
    private List<Interest> interestList;

    public SoftSkillCourse(String courseName, String courseDescription, int courseLevel, List<Interest> interestList) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseLevel = courseLevel;
        this.interestList = interestList;
        this.careerPaths = new ArrayList<>();
    }

    public SoftSkillCourse(String courseName, String courseDescription, int courseLevel) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseLevel = courseLevel;
        this.careerPaths = new ArrayList<>();
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
