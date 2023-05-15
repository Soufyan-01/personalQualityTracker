package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional

public interface SpringSoftSkillCourseRepository extends JpaRepository<SoftSkillCourse, Long> {

    Optional<SoftSkillCourse> findSoftSkillCourseByCourseName(String email);

}
