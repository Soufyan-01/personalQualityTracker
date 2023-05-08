package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.CareerPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository

public interface SpringCareerPathRespository extends JpaRepository<CareerPath, Long> {
//    Optional<CareerPath> findByName(String name);

    void deleteByName(String name);
}
