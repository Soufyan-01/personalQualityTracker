package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringSoftSkillCourseRepository;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SoftSkillCourseService {

    private final SpringSoftSkillCourseRepository springSoftSkillCourseRepository;

    private final CareerPathService careerPathService;

    public SoftSkillCourseService(SpringSoftSkillCourseRepository springSoftSkillCourseRepository, CareerPathService careerPathService) {
        this.springSoftSkillCourseRepository = springSoftSkillCourseRepository;
        this.careerPathService = careerPathService;
    }

    public SoftSkillCourse getSoftSkillCourseById(Long id) {
        return springSoftSkillCourseRepository.findById(id).get();
    }


    public SoftSkillCourse createNewSoftSkill(String courseName, String courseDescription, int courseLevel){

        SoftSkillCourse softSkillCourse = new SoftSkillCourse(courseName, courseDescription, courseLevel);

        return springSoftSkillCourseRepository.save(softSkillCourse);
    }

    public SoftSkillCourse addCareerPathsToSoftSkillCourse(Long courseId, List<Long> careerPath) {
        SoftSkillCourse softSkillCourse = getSoftSkillCourseById(courseId);

        List<CareerPath> careerPaths = new ArrayList<>();

        for (Long careerPathId : careerPath) {
            CareerPath careerPath2 = careerPathService.getCareerPathById(careerPathId)
                    .orElseThrow(() -> new EntityNotFoundException("Career path with id " + careerPathId + " not found"));
            careerPaths.add(careerPath2);
            softSkillCourse.addCareerPathToSoftSkillCourse(careerPath2);

        }

        System.out.println(careerPaths);

        softSkillCourse.setCareerPaths(softSkillCourse.getCareerPaths());

        System.out.println(softSkillCourse.getCareerPaths());

        return springSoftSkillCourseRepository.save(softSkillCourse);
    }




}
