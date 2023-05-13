package com.example.personalQualityTracker.development.data;

import com.example.personalQualityTracker.development.domain.HardSkillCourse;
import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Transactional
public interface SpringHardSkillCourseRepository extends JpaRepository<HardSkillCourse, Long> {

    Optional<HardSkillCourse> findHardSkillCourseByCourseName(String email);

}
