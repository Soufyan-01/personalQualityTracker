package com.example.personalQualityTracker.development.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "HardSkillCourse")
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class HardSkillCourse implements Serializable {

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
    @JoinTable(name = "hard_skill_course_career_paths",
            joinColumns = @JoinColumn(name = "hard_skill_course_id"),
            inverseJoinColumns = @JoinColumn(name = "career_paths_id"),
            uniqueConstraints = {},
            indexes = {})
    private List<CareerPath> careerPaths;

    public HardSkillCourse(String courseName, String courseDescription, int courseLevel) {
        this.courseName = courseName;
        this.courseDescription = courseDescription;
        this.courseLevel = courseLevel;
    }

    public HardSkillCourse() {
    }

    public void addCareerPathToHardSkillCourse(CareerPath careerPath) {
        careerPaths.add(careerPath);
    }
}
