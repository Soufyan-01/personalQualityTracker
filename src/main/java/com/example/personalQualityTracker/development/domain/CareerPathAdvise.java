package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Interest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Entity
@Component
@Table(name = "CareerPathAdvise")
public class CareerPathAdvise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private CareerPath careerPath;

    public CareerPathAdvise(CareerPath careerPath) {
        this.careerPath = careerPath;
    }

    public CareerPathAdvise() {
    }

    public HashMap<String, Integer> findPercentageByCareerPath(List<CareerPath> careerPaths, Interest interestOne, Interest interestTwo, Interest interestThree, int interestPercentageOne, int interestPercentageTwo, int interestPercentageThree) {
        HashMap<String, Integer> careerPathsThatHaveSimilarities = new HashMap<>();

        int totalMatchingInterests = 0;
        int totalImportance = 0;

        for (CareerPath careerPath : careerPaths) {
            List<Interest> careerPathInterests = careerPath.getInterestList();

            // Check the number of matching interests between the candidate and the career path
            int matchingInterests = 0;
            int pathImportance = 0;
            for (Interest interest : careerPathInterests) {
                if (interest.equals(interestOne) || interest.equals(interestTwo) || interest.equals(interestThree)) {
                    matchingInterests++;
                    if (interest.equals(interestOne)) {
                        pathImportance += interestPercentageOne;
                    } else if (interest.equals(interestTwo)) {
                        pathImportance += interestPercentageTwo;
                    } else if (interest.equals(interestThree)) {
                        pathImportance += interestPercentageThree;
                    }
                }
            }

            totalMatchingInterests += matchingInterests;
            totalImportance += pathImportance;

            // Add the career path and matching percentage to the hashmap
            careerPathsThatHaveSimilarities.put(careerPath.getName(), pathImportance);
        }

        // Calculate the matching percentages as fractions of the total matching interests and importance
        for (String careerPath : careerPathsThatHaveSimilarities.keySet()) {
            int pathImportance = careerPathsThatHaveSimilarities.get(careerPath);
            int matchingPercentage = totalImportance != 0 ? (pathImportance * 100) / totalImportance : 0;
            careerPathsThatHaveSimilarities.put(careerPath, matchingPercentage);
        }

        return careerPathsThatHaveSimilarities;
    }

    @Override
    public String toString() {
        return "CareerPathAdvise{" +
                "careerPath=" + careerPath.getName() +
                '}';
    }

}
