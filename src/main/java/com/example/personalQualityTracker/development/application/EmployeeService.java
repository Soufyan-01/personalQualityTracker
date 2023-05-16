package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringEmployeeRepository;
import com.example.personalQualityTracker.development.domain.*;
import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.security.application.UserService;
import com.example.personalQualityTracker.security.data.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional
public class EmployeeService {
    private final SpringEmployeeRepository springEmployeeRepository;

    private final UserService userService;

    private final SoftSkillCourseService softSkillCourseService;

    private final CareerPathService careerPathService;

    private final HardSkillCourseService hardSkillCourseService;

    public EmployeeService(SpringEmployeeRepository springEmployeeRepository, UserService userService, SoftSkillCourseService softSkillCourseService, CareerPathService careerPathService, HardSkillCourseService hardSkillCourseService) {
        this.springEmployeeRepository = springEmployeeRepository;
        this.userService = userService;
        this.softSkillCourseService = softSkillCourseService;
        this.careerPathService = careerPathService;
        this.hardSkillCourseService = hardSkillCourseService;
    }

    public void createNewEmployee(String name, String surname, String email, Function function) throws IOException {
        Employee employee = new Employee(name, surname, email, email, function);

        User user = userService.loadUserByUsername(email);

        if (employee.isValid(email)) {

                if(user.getPerson() != null) {
                    throw new IllegalArgumentException("This user already exists!");
                }
                user.setEmployee(employee);
                user.setPerson(employee);

            springEmployeeRepository.save(employee);
        }
    }

    public Employee updateEmployee(Long id, String image, String email) {
        Employee employee = getEmployeeById(id);
        employee.setEmail(email);
        return springEmployeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        Optional<Employee> employeeOptional = springEmployeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            return employeeOptional.get();
        } else {
            throw new NoSuchElementException("Employee not found");
        }
    }


    public Employee getEmployeeIdByEmail(String email) {
        return springEmployeeRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }

    public void deleteEmployee(Long id) {
        springEmployeeRepository.deleteById(id);
    }

    public List<Employee> getAllEmployees() {
        return springEmployeeRepository.findAll();
    }


    public Employee addCareerPathToEmployee(Long employeeId, Long careerPathId) {
        Employee employee = getEmployeeById(employeeId);
        CareerPath careerPath = careerPathService.getCareerPathById(careerPathId)
                .orElseThrow(() -> new EntityNotFoundException("Career path with id " + careerPathId + " not found"));


        employee.setCareerPath(careerPath);
        return springEmployeeRepository.save(employee);
    }

    public Long addSoftSkillCourseToEmployee(Long employeeId, Long courseId) {
        Employee employee = getEmployeeById(employeeId);

        return employee.addSoftSkillCourse(softSkillCourseService.getSoftSkillCourseById(courseId));
    }

    public Long addSoftHardCourseToEmployee(Long employeeId, Long courseId) {
        Employee employee = getEmployeeById(employeeId);

        return employee.addHardSkillCourse(hardSkillCourseService.getHardSkillCourseById(courseId));
    }


}