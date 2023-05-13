package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.lang.Math.min;
import static java.util.Collections.max;

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

    public Employee() {
    }

    public Employee(String name, String surname, String loginEmail,String email, Function function) {
        super(name, surname, loginEmail, function);
        this.email = email;
    }

    public Employee(CareerPath careerPath) {
        this.careerPath = careerPath;
    }

    public void addSoftSkillCourse(SoftSkillCourse softSkillCourse) {
        softSkillCourses.add(softSkillCourse);
    }


    public boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
