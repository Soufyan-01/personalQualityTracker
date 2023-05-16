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
@Table(name = "CareerPath")
public class CareerPath implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.ORDINAL)
    @ElementCollection(targetClass = Interest.class)
    private List<Interest> interestList;



    public CareerPath(String name, String description, List<Interest> interestList) {
        this.name = name;
        this.description = description;
        this.interestList = interestList;
    }

    public CareerPath() {
    }

}
