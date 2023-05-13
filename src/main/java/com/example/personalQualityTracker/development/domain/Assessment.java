package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.application.CareerPathService;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.example.personalQualityTracker.development.domain.Enum.question.*;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@Entity
@Table(name = "Assessment")
public class Assessment {

    @Id
    @GeneratedValue
    private Long id;

    @Column
    private QuestionOne questionOne;

    @Column
    private QuestionTwo questionTwo;

    @Column
    private QuestionThree questionThree;

    @Column
    private QuestionFour questionFour;

    @Column
    private QuestionFive questionFive;

    @Column
    private QuestionSix questionSix;

    @Column
    private QuestionSeven questionSeven;

    @Column
    private QuestionEight questionEight;

    @Column
    private String interestOne;

    @Column
    private String interestTwo;

    @Column
    private String interestThree;
    @Column
    private int percentageInterestOne;

    @Column
    private int percentageInterestTwo;
    @Column
    private int percentageInterestThree;

    @OneToOne
    private Person person;

    @OneToOne
    private CareerPath careerPath;

    public Assessment(CareerPath careerPath) {
        this.careerPath = careerPath;
    }


    public Assessment(QuestionOne questionOne, QuestionTwo questionTwo, QuestionThree questionThree, QuestionFour questionFour, QuestionFive questionFive, QuestionSix questionSix, QuestionSeven questionSeven, QuestionEight questionEight) {
        this.questionOne = questionOne;
        this.questionTwo = questionTwo;
        this.questionThree = questionThree;
        this.questionFour = questionFour;
        this.questionFive = questionFive;
        this.questionSix = questionSix;
        this.questionSeven = questionSeven;
        this.questionEight = questionEight;
    }

    public Assessment(QuestionOne questionOne, QuestionTwo questionTwo, QuestionThree questionThree, QuestionFour questionFour, QuestionFive questionFive, QuestionSix questionSix, QuestionSeven questionSeven, QuestionEight questionEight, String interestOne, String interestTwo, String interestThree, int percentageInterestOne, int percentageInterestTwo, int percentageInterestThree) {
        this.questionOne = questionOne;
        this.questionTwo = questionTwo;
        this.questionThree = questionThree;
        this.questionFour = questionFour;
        this.questionFive = questionFive;
        this.questionSix = questionSix;
        this.questionSeven = questionSeven;
        this.questionEight = questionEight;
        this.interestOne = interestOne;
        this.interestTwo = interestTwo;
        this.interestThree = interestThree;
        this.percentageInterestOne = percentageInterestOne;
        this.percentageInterestTwo = percentageInterestTwo;
        this.percentageInterestThree = percentageInterestThree;
    }

    public Assessment() {
    }

    public Map<String, Integer> matchHardSkillCourses(List<HardSkillCourse> hardSkillCourses, CareerPath careerPath) {
        Map<String, Integer> matchingCourse = new HashMap<>();
        for (HardSkillCourse hardSkillCourse : hardSkillCourses) {
            for (CareerPath careerPath1: hardSkillCourse.getCareerPaths()){
                if (careerPath1.equals(careerPath)){
                    matchingCourse.put(hardSkillCourse.getCourseName(), 100);
                }
            }
        }
        return matchingCourse;
    }



    public Map<String, Integer> matchingSoftSkillCourses(Interest interestOne, Interest interestTwo, Interest interestThree,
                                                                  List<SoftSkillCourse> softSkillCourses, CareerPath careerPath) {

        Map<String, Integer> matchingCourses = new HashMap<>();

        for (SoftSkillCourse softSkillCourse : softSkillCourses) {
            int matchingPercentage = 0;

            if (careerPath == null || softSkillCourse.getCareerPaths().contains(careerPath)) {
                List<Interest> interests = softSkillCourse.getInterestList();
                int numInterests = interests.size();

                for (Interest interest : interests) {
                    if (interest.equals(interestOne)) {
                        matchingPercentage += 50;
                    } else if (interest.equals(interestTwo)) {
                        matchingPercentage += 30;
                    } else if (interest.equals(interestThree)) {
                        matchingPercentage += 20;
                    }
                }

                if (matchingPercentage > 0) {
                    matchingPercentage = (int) Math.round((double) matchingPercentage / numInterests);
                    matchingCourses.put(softSkillCourse.getCourseName(), matchingPercentage);
                }
            }
        }

        matchingCourses = sortByValue(matchingCourses);

        System.out.println(matchingCourses);

        return matchingCourses;
    }

    // A helper function to sort the map by value
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        return map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));
    }

    public InterestPercentage[] determinePersonality(QuestionOne questionOne, QuestionTwo questionTwo, QuestionThree questionThree, QuestionFour questionFour, QuestionFive questionFive, QuestionSix questionSix, QuestionSeven questionSeven, QuestionEight questionEight) {
        int interestOne = 0;
        int interestTwo = 0;
        int interestThree = 0;
        int interestFour = 0;
        int interestFive = 0;
        int interestSix = 0;

        if (questionOne.equals(QuestionOne.ONE)) {
            interestOne += 1;
            interestFour += 1;
        } else if (questionOne.equals(QuestionOne.TWO)) {
            interestTwo += 1;
            interestFive += 1;
        } else if (questionOne.equals(QuestionOne.THREE)) {
            interestSix += 1;
            interestTwo += 1;
        } else {
            interestFour += 1;
        }

        if (questionTwo.equals(QuestionTwo.ONE)) {
            interestOne += 1;
            interestTwo += 1;
        } else if (questionTwo.equals(QuestionTwo.TWO)) {
            interestSix += 1;
            interestFour += 1;
        } else if (questionTwo.equals(QuestionTwo.THREE)) {
            interestFive += 1;
        } else {
            interestFour += 1;
            interestSix += 1;
        }

        if (questionThree.equals(QuestionThree.ONE)) {
            interestOne += 1;
            interestTwo += 1;
        } else if (questionThree.equals(QuestionThree.TWO)) {
            interestTwo += 1;
            interestFour += 1;
        } else if (questionThree.equals(QuestionThree.THREE)) {
            interestThree += 1;
            interestOne += 1;
        } else {
            interestFive += 1;
            interestSix += 1;
        }
        if (questionFour.equals(QuestionFour.ONE)) {
            interestOne += 1;
            interestFour += 1;
        } else if (questionFour.equals(QuestionFour.TWO)) {
            interestTwo += 1;
            interestFive += 1;
        } else if (questionFour.equals(QuestionFour.THREE)) {
            interestThree += 1;
            interestSix += 1;
        } else {
            interestFour += 1;
        }

        if (questionFive.equals(QuestionFive.ONE)) {
            interestOne += 1;
            interestFour += 1;
        } else if (questionFive.equals(QuestionFive.TWO)) {
            interestTwo += 1;
            interestFive += 1;
        } else if (questionFive.equals(QuestionFive.THREE)) {
            interestThree += 1;
            interestSix += 1;
        } else {
            interestFour += 1;
        }

        if (questionSix.equals(QuestionSix.ONE)) {
            interestTwo += 1;
            interestThree += 1;
        } else if (questionSix.equals(QuestionSix.TWO)) {
            interestTwo += 1;
            interestFive += 1;
        } else if (questionSix.equals(QuestionSix.THREE)) {
            interestFour += 1;
            interestSix += 1;
        } else {
            interestFour += 1;
        }

        InterestPercentage[] topInterests = new InterestPercentage[3];
        int[] interests = {interestOne, interestTwo, interestThree, interestFour, interestFive, interestSix};
        Set<Integer> usedInterests = new HashSet<>();
        int totalInterestCount = 0;
        for (int interestCount : interests) {
            totalInterestCount += interestCount;
        }
        for (int i = 0; i < 3; i++) {
            int maxInterest = -1;
            double maxInterestPercentage = 0.0;
            for (int j = 0; j < interests.length; j++) {
                double interestPercentage = (double) interests[j] / totalInterestCount;
                if (!usedInterests.contains(j) && interestPercentage > maxInterestPercentage) {
                    maxInterest = j;
                    maxInterestPercentage = interestPercentage;
                }
            }
            usedInterests.add(maxInterest);
            Interest interest = Interest.values()[maxInterest];
            String interestName = interest.getName();
            double interestPercentage = maxInterestPercentage * 100;

            topInterests[i] = new InterestPercentage(interestName, interestPercentage);
        }

        return topInterests;

    }

    @Override
    public String toString() {
        return "Assessment{" +
                "careerPath=" + careerPath.getName() +
                '}';
    }
}