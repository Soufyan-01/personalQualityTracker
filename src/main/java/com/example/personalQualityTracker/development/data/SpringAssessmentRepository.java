package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.Assessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional

public interface SpringAssessmentRepository extends JpaRepository<Assessment, Long> {
    Optional<Assessment> findAssessmentByPersonId(Long id);

}
