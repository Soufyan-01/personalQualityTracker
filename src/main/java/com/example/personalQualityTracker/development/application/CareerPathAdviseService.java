package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringCareerPathAdviseRespository;
import com.example.personalQualityTracker.development.domain.Assessment;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.domain.CareerPathAdvise;
import com.example.personalQualityTracker.development.domain.Employee;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

import static com.example.personalQualityTracker.development.domain.Enum.Interest.CONVENTIONAL;

@Service
@Transactional
public class CareerPathAdviseService {

    private final CareerPathService careerPathService;

    private final AssessmentService assessmentService;

    private final CareerPathAdvise careerPathAdvise;


    public CareerPathAdviseService(CareerPathService careerPathService, AssessmentService assessmentService, CareerPathAdvise careerPathAdvise) {
        this.careerPathService = careerPathService;
        this.assessmentService = assessmentService;
        this.careerPathAdvise = careerPathAdvise;
    }

    public HashMap<String, Integer> getCareerPathAdviseWithPercentage(Long id){

        List<CareerPath> careerPath = careerPathService.getAllCareerPaths();

        Assessment assessment = assessmentService.getAssessmentByPersonId(id);

        Interest interestOne = Interest.valueOf(assessment.getInterestOne().toUpperCase());
        Interest interestTwo = Interest.valueOf(assessment.getInterestTwo().toUpperCase());;
        Interest interestThree = Interest.valueOf(assessment.getInterestThree().toUpperCase());;

        int interestOnePercentage = assessment.getPercentageInterestOne();
        int interestTwoPercentage = assessment.getPercentageInterestTwo();
        int interestThreePercentage = assessment.getPercentageInterestThree();

        return careerPathAdvise.findPercentageByCareerPath(careerPath, interestOne, interestTwo, interestThree, interestOnePercentage, interestTwoPercentage, interestThreePercentage);

    }
}
