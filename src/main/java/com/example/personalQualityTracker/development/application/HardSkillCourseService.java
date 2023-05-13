package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringHardSkillCourseRepository;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.example.personalQualityTracker.development.domain.HardSkillCourse;
import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class HardSkillCourseService {

    private final SpringHardSkillCourseRepository springHardSkillCourseRepository;

    private final CareerPathService careerPathService;

    public HardSkillCourseService(SpringHardSkillCourseRepository springHardSkillCourseRepository, CareerPathService careerPathService) {
        this.springHardSkillCourseRepository = springHardSkillCourseRepository;
        this.careerPathService = careerPathService;
    }

    public HardSkillCourse getHardSkillCourseById(Long id) {
        return springHardSkillCourseRepository.findById(id).get();
    }


    public List<HardSkillCourse> getAllHardSkillCourses() {
        return springHardSkillCourseRepository.findAll();
    }

    public HardSkillCourse getHardSkillCourseBasedOnId(Long id) {
        return springHardSkillCourseRepository.getById(id);
    }

    public HardSkillCourse createNewHardSkillCourse(String courseName, String courseDescription, int courseLevel){

        HardSkillCourse hardSkillCourse = new HardSkillCourse(courseName, courseDescription, courseLevel);

        return springHardSkillCourseRepository.save(hardSkillCourse);
    }

    public HardSkillCourse addCareerPathsToHardSkillCourse(Long courseId, List<Long> careerPath) {
        HardSkillCourse hardSkillCourse = getHardSkillCourseById(courseId);

        List<CareerPath> careerPaths = new ArrayList<>();

        System.out.println(careerPath);

        for (Long careerPathId : careerPath) {
            CareerPath careerPath2 = careerPathService.getCareerPathById(careerPathId)
                    .orElseThrow(() -> new EntityNotFoundException("Career path with id " + careerPathId + " not found"));
            careerPaths.add(careerPath2);
            hardSkillCourse.addCareerPathToHardSkillCourse(careerPath2);

        }

        System.out.println(careerPaths);

        hardSkillCourse.setCareerPaths(hardSkillCourse.getCareerPaths());

        System.out.println(hardSkillCourse.getCareerPaths());

        return springHardSkillCourseRepository.save(hardSkillCourse);
    }

    public Long getHardSkilLCourseByName(String name) {
        return springHardSkillCourseRepository.findHardSkillCourseByCourseName(name).get().getId();
    }

}
