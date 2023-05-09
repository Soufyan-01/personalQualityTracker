package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.CareerPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SpringCareerPathRespository extends JpaRepository<CareerPath, Long> {
    CareerPath findByName(String name);

    void deleteByName(String name);
}
