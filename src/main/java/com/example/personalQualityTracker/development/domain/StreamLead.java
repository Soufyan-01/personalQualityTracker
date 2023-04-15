package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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

    public StreamLead(String name, String surname,String email, Function function) {
        super(name, surname, function);
        this.email = email;
    }

}
