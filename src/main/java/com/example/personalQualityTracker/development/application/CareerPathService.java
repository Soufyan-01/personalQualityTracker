package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringCareerPathRespository;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.domain.CareerPathAdvise;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CareerPathService {

    private final SpringCareerPathRespository springCareerPathRespository;

    public CareerPathService(SpringCareerPathRespository springCareerPathRespository) {
        this.springCareerPathRespository = springCareerPathRespository;
    }

    public CareerPath addNewCareerPath(String name, String description, List<Interest> interestList){
        CareerPath careerPath = new CareerPath(name, description, interestList);

        return springCareerPathRespository.save(careerPath);
    }

    public void deleteCareerPath(String name) {
        springCareerPathRespository.deleteByName(name);
    }

    public List<CareerPath> getAllCareerPaths() {
        return springCareerPathRespository.findAll();
    }

}
