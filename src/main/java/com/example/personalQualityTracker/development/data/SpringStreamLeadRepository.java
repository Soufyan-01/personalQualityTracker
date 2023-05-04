package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.Employee;
import com.example.personalQualityTracker.development.domain.StreamLead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpringStreamLeadRepository extends JpaRepository<StreamLead, Long> {
    Optional<StreamLead> findByEmail(String email);

}
