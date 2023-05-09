package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional

public interface SpringSoftSkillCourseRepository extends JpaRepository<SoftSkillCourse, Long> {
}
