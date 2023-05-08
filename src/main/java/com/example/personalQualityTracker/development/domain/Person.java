package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "Person")
public class Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String loginEmail;

    @Column
    private Function function;

//    Needs to be implemented if needed --> Maybe only by person
    @Column
    private Interest interest;

    public Person(String name, String surname, String loginEmail, Function function) {
        this.name = name;
        this.surname = surname;
        this.loginEmail = loginEmail;
        this.function = function;
    }

//    public Person(Interest interest) {
//        this.interest = interest;
//    }

    public Person() {
    }
}
