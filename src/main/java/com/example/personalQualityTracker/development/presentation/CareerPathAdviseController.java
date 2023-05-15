package com.example.personalQualityTracker.development.presentation;

import com.example.personalQualityTracker.development.application.CareerPathAdviseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api/careerPathAdvise")
@CrossOrigin("http://localhost:3000/")
public class CareerPathAdviseController {

    private final CareerPathAdviseService careerPathAdviseService;

    public CareerPathAdviseController(CareerPathAdviseService careerPathAdviseService) {
        this.careerPathAdviseService = careerPathAdviseService;
    }

    @GetMapping("/advise/{id}")
    public ResponseEntity<HashMap<String, Integer>> getCareerPathAdviseWithPercentage(@PathVariable Long id) {
        HashMap<String, Integer> careerPathAdvise = careerPathAdviseService.getCareerPathAdviseWithPercentage(id);
        if (careerPathAdvise.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(careerPathAdvise);
    }
}
