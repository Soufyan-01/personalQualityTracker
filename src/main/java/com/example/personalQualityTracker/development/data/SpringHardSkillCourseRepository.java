package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.HardSkillCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface SpringHardSkillCourseRepository extends JpaRepository<HardSkillCourse, Long> {
}
