package com.example.personalQualityTracker.development.presentation;

import com.example.personalQualityTracker.development.application.CareerPathService;
import com.example.personalQualityTracker.development.domain.Assessment;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.presentation.DTO.AssessmentDTO;
import com.example.personalQualityTracker.development.presentation.DTO.CareerPathDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/careerPath")
@CrossOrigin("http://localhost:3000/")
public class CareerPathController {

    private final CareerPathService careerPath;

    public CareerPathController(CareerPathService careerPath) {
        this.careerPath = careerPath;
    }

    @PostMapping("/careerPath")
    public CareerPath newAssessment(@RequestBody CareerPathDTO careerPathDTO) throws IOException {
        return careerPath.addNewCareerPath(careerPathDTO.name, careerPathDTO.description, careerPathDTO.interestList);
    }

    @DeleteMapping("/deleteCareerPath/{name}")
    public void deleteCareerPath(@PathVariable("name") String name) {
        careerPath.deleteCareerPath(name);
    }

    @GetMapping("/paths")
    public List<CareerPath> getAllCareerPaths() {
        return careerPath.getAllCareerPaths();
    }


}
