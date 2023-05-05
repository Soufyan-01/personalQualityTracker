package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface SpringEmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);

    Optional<Employee> deleteEmployeeByEmail(String email);

}
