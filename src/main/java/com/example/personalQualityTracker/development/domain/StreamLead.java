package com.example.personalQualityTracker.development.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.regex.Pattern;

@Getter
@Setter
@Entity
@Table(name = "StreamLead")
public class StreamLead extends Person{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    public StreamLead() {
    }

    public StreamLead(String name, String surname,String email) {
        super(name, surname);
        this.email = email;
    }

}
