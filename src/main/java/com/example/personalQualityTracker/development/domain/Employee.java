package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;


@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @OneToOne
    private CareerPath careerPath;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<SoftSkillCourse> softSkillCourses;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<HardSkillCourse> hardSkillCourses;

    public Employee() {
    }

    public Employee(String name, String surname, String loginEmail,String email, Function function) {
        super(name, surname, loginEmail, function);
        this.email = email;
    }

    public Long addSoftSkillCourse(SoftSkillCourse softSkillCourse) {
        softSkillCourses.add(softSkillCourse);
        return null;
    }

    public Long addHardSkillCourse(HardSkillCourse hardSkillCourse) {
        hardSkillCourses.add(hardSkillCourse);
        return null;
    }

    public boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
