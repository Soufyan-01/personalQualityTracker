package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringHardSkillCourseRepository;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.example.personalQualityTracker.development.domain.HardSkillCourse;
import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
@SpringBootTest
class HardSkillCourseServiceTest {

    @Mock
    private SpringHardSkillCourseRepository springHardSkillCourseRepository;

    @Mock
    private CareerPathService careerPathService;

    @InjectMocks
    private HardSkillCourseService hardSkillCourseService;

    @Test
    void getAllHardSkillCourses_ReturnsListOfCourses() {
        // Arrange
        List<HardSkillCourse> expectedCourses = new ArrayList<>();
        expectedCourses.add(new HardSkillCourse("Career Path 1", "oke",  2));
        expectedCourses.add(new HardSkillCourse("Career Path 1", "oke",  2));
        when(springHardSkillCourseRepository.findAll()).thenReturn(expectedCourses);

        // Act
        List<HardSkillCourse> result = hardSkillCourseService.getAllHardSkillCourses();

        // Assert
        assertEquals(expectedCourses, result);
        verify(springHardSkillCourseRepository, times(1)).findAll();
    }

    @Test
    void getHardSkillCourseBasedOnId_ExistingCourseId_ReturnsCourse() {
        // Arrange
        Long courseId = 1L;
        HardSkillCourse expectedCourse = new HardSkillCourse("Career Path 1", "oke",  2);
        when(springHardSkillCourseRepository.getById(courseId)).thenReturn(expectedCourse);

        // Act
        HardSkillCourse result = hardSkillCourseService.getHardSkillCourseBasedOnId(courseId);

        // Assert
        assertEquals(expectedCourse, result);
        verify(springHardSkillCourseRepository, times(1)).getById(courseId);
    }



    @Test
    void createNewHardSkillCourse_ValidData_ReturnsCreatedHardSkillCourse() {
        // Arrange
        String courseName = "CourseName";
        String courseDescription = "CourseDescription";
        int courseLevel = 2;
        HardSkillCourse hardSkillCourse = new HardSkillCourse(courseName, courseDescription, courseLevel);
        when(springHardSkillCourseRepository.save(any(HardSkillCourse.class))).thenReturn(hardSkillCourse);

        // Act
        HardSkillCourse result = hardSkillCourseService.createNewHardSkillCourse(courseName, courseDescription, courseLevel);

        // Assert
        assertNotNull(result);
        assertEquals(courseName, result.getCourseName());
        assertEquals(courseDescription, result.getCourseDescription());
        assertEquals(courseLevel, result.getCourseLevel());
        verify(springHardSkillCourseRepository, times(1)).save(any(HardSkillCourse.class));
    }

    @Test
    void addCareerPathsToHardSkillCourse_ValidData_ReturnsUpdatedHardSkillCourse() {
        // Arrange
        Long courseId = 1L;
        List<Long> careerPathIds = List.of(1L, 2L, 3L);
        HardSkillCourse hardSkillCourse = new HardSkillCourse("CourseName", "CourseDescription", 2);
        when(springHardSkillCourseRepository.findById(courseId)).thenReturn(Optional.of(hardSkillCourse));

        List<CareerPath> careerPaths = List.of(
                new CareerPath( "Career Path 1", "oke",  java.util.Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE)),
                new CareerPath("Career Path 1", "oke",  java.util.Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE)),
                new CareerPath("Career Path 1", "oke",  java.util.Arrays.asList(Interest.REALISTIC, Interest.INVESTIGATIVE))
        );
        when(careerPathService.getCareerPathById(anyLong())).thenReturn(Optional.of(careerPaths.get(0)), Optional.of(careerPaths.get(1)), Optional.of(careerPaths.get(2)));

        when(springHardSkillCourseRepository.save(any(HardSkillCourse.class))).thenReturn(hardSkillCourse);

        // Act
        HardSkillCourse result = hardSkillCourseService.addCareerPathsToHardSkillCourse(courseId, careerPathIds);

        // Assert
        assertNotNull(result);
        assertEquals(careerPathIds.size(), result.getCareerPaths().size());
        assertTrue(result.getCareerPaths().containsAll(careerPaths));
        verify(springHardSkillCourseRepository, times(1)).findById(courseId);
        verify(careerPathService, times(careerPathIds.size())).getCareerPathById(anyLong());
        verify(springHardSkillCourseRepository, times(1)).save(any(HardSkillCourse.class));
    }

    @Test
    void getHardSkilLCourseByName_ExistingCourseName_ReturnsCourseId() {
        // Arrange
        String courseName = "CourseName";
        HardSkillCourse hardSkillCourse = new HardSkillCourse("Career Path 1", "oke",  2);
        when(springHardSkillCourseRepository.findHardSkillCourseByCourseName(courseName)).thenReturn(Optional.of(hardSkillCourse));

        // Act
        Long result = hardSkillCourseService.getHardSkilLCourseByName(courseName);

        // Assert
//        assertNotNull(result);
        assertEquals(hardSkillCourse.getId(), result);
        verify(springHardSkillCourseRepository, times(1)).findHardSkillCourseByCourseName(courseName);
    }


}