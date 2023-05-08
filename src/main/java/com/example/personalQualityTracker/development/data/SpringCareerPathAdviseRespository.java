package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.CareerPathAdvise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SpringCareerPathAdviseRespository extends JpaRepository<CareerPathAdvise, Long> {
}
