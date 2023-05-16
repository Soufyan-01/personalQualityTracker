package com.example.personalQualityTracker.development.application;

import static org.junit.jupiter.api.Assertions.*;

import com.example.personalQualityTracker.development.data.SpringEmployeeRepository;
import com.example.personalQualityTracker.development.domain.CareerPath;
import com.example.personalQualityTracker.development.domain.Employee;
import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import com.example.personalQualityTracker.development.domain.HardSkillCourse;
import com.example.personalQualityTracker.development.domain.SoftSkillCourse;
import com.example.personalQualityTracker.security.application.UserService;
import com.example.personalQualityTracker.security.data.User;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceTest {

    @Mock
    private SpringEmployeeRepository springEmployeeRepository;

    @Mock
    private UserService userService;

    @Mock
    private SoftSkillCourseService softSkillCourseService;

    @Mock
    private CareerPathService careerPathService;

    @Mock
    private HardSkillCourseService hardSkillCourseService;

    @InjectMocks
    private EmployeeService employeeService;

    public EmployeeServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createNewEmployee_UserAlreadyExists_ShouldThrowIllegalArgumentException() throws IOException {
        // Arrange
        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        Function function = Function.EMPLOYEE;

        User mockUser = new User();
        mockUser.setPerson(new Employee());

        when(userService.loadUserByUsername(email)).thenReturn(mockUser);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> employeeService.createNewEmployee(name, surname, email, function));
        verify(springEmployeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void updateEmployee_ValidData_ShouldUpdateEmployee() {
        // Arrange
        Long id = 1L;
        String image = "profile.jpg";
        String email = "john.doe@example.com";
        Employee mockEmployee = new Employee();
        mockEmployee.setId(id);

        Optional<Employee> optionalEmployee = Optional.of(mockEmployee);

        when(springEmployeeRepository.findById(id)).thenReturn(optionalEmployee);
        when(springEmployeeRepository.save(mockEmployee)).thenReturn(mockEmployee);

        // Act
        Employee updatedEmployee = employeeService.updateEmployee(id, image, email);

        // Assert
        verify(springEmployeeRepository, times(1)).findById(id);
        verify(springEmployeeRepository, times(1)).save(mockEmployee);
        assertEquals(email, updatedEmployee.getEmail());
    }


    @Test
    void addCareerPathToEmployee_ValidData_ShouldReturnEmployeeWithCareerPath() {
        // Arrange
        Long employeeId = 1L;
        Long careerPathId = 1L;
        Employee employee = new Employee();
        employee.setId(employeeId);
        CareerPath careerPath = new CareerPath();
        careerPath.setId(careerPathId);

        when(springEmployeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(careerPathService.getCareerPathById(careerPathId)).thenReturn(Optional.of(careerPath));
        when(springEmployeeRepository.save(employee)).thenReturn(employee);

        // Act
        Employee result = employeeService.addCareerPathToEmployee(employeeId, careerPathId);

        // Assert
        verify(springEmployeeRepository, times(1)).findById(employeeId);
        verify(careerPathService, times(1)).getCareerPathById(careerPathId);
        verify(springEmployeeRepository, times(1)).save(employee);
        assertEquals(careerPath, result.getCareerPath());
    }

    @Test
    void deleteEmployee_ValidId_ShouldDeleteEmployee() {
        // Arrange
        Long employeeId = 1L;

        // Act
        employeeService.deleteEmployee(employeeId);

        // Assert
        verify(springEmployeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void getAllEmployees_NoEmployees_ShouldReturnEmptyList() {
        // Arrange
        List<Employee> emptyList = Collections.emptyList();
        when(springEmployeeRepository.findAll()).thenReturn(emptyList);

        // Act
        List<Employee> result = employeeService.getAllEmployees();

        // Assert
        verify(springEmployeeRepository, times(1)).findAll();
        assertEquals(emptyList, result);
    }

    @Test
    void getEmployeeIdByEmail_ExistingEmail_ShouldReturnEmployee() {
        // Arrange
        String email = "test@example.com";
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setEmail(email);

        when(springEmployeeRepository.findByEmail(email)).thenReturn(Optional.of(employee));

        // Act
        Employee result = employeeService.getEmployeeIdByEmail(email);

        // Assert
        verify(springEmployeeRepository, times(1)).findByEmail(email);
        assertEquals(employee, result);
    }

    @Test
    void getEmployeeIdByEmail_NonExistingEmail_ShouldThrowException() {
        // Arrange
        String email = "nonexisting@example.com";

        when(springEmployeeRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            employeeService.getEmployeeIdByEmail(email);
        });
    }



}