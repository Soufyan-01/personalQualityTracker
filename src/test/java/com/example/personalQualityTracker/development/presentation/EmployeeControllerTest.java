package com.example.personalQualityTracker.development.presentation;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.example.personalQualityTracker.development.application.EmployeeService;
import com.example.personalQualityTracker.development.application.StreamLeadService;
import com.example.personalQualityTracker.development.domain.Employee;
import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.development.domain.StreamLead;
import com.example.personalQualityTracker.development.presentation.DTO.EmployeeDTO;
import com.example.personalQualityTracker.development.presentation.DTO.StreamLeadDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @Mock
    private StreamLeadService streamLeadService;

    @InjectMocks
    private EmployeeController employeeController;

    public EmployeeControllerTest() {
        MockitoAnnotations.initMocks(this);
    }



    @Test
    public void getStreamLeadIdByEmail_ReturnsStreamLead() {
        // Arrange
        String email = "jane.doe@example.com";
        StreamLead expectedStreamLead = new StreamLead();

        when(streamLeadService.getStreamLeadIdByEmail(email)).thenReturn(expectedStreamLead);

        // Act
        StreamLead result = employeeController.getStreamLeadIdByEmail(email);

        // Assert
        assertEquals(expectedStreamLead, result);
        verify(streamLeadService, times(1)).getStreamLeadIdByEmail(email);
    }


    @Test
    public void getEmployeeIdByEmail_ReturnsEmployee() {
        // Arrange
        String email = "john.doe@example.com";
        Employee expectedEmployee = new Employee();

        when(employeeService.getEmployeeIdByEmail(email)).thenReturn(expectedEmployee);

        // Act
        Employee result = employeeController.getEmployeeIdByEmail(email);

        // Assert
        assertEquals(expectedEmployee, result);
        verify(employeeService, times(1)).getEmployeeIdByEmail(email);
    }

    @Test
    public void getAssignmentById_ReturnsEmployee() {
        // Arrange
        Long id = 1L;
        Employee expectedEmployee = new Employee();

        when(employeeService.getEmployeeById(id)).thenReturn(expectedEmployee);

        // Act
        Employee result = employeeController.getAssignmentById(id);

        // Assert
        assertEquals(expectedEmployee, result);
        verify(employeeService, times(1)).getEmployeeById(id);
    }

    @Test
    public void employees_ReturnsListOfEmployees() {
        // Arrange
        List<Employee> expectedEmployees = new ArrayList<>();
        expectedEmployees.add(new Employee());
        expectedEmployees.add(new Employee());

        when(employeeService.getAllEmployees()).thenReturn(expectedEmployees);

        // Act
        List<Employee> result = employeeController.employees();

        // Assert
        assertEquals(expectedEmployees, result);
        verify(employeeService, times(1)).getAllEmployees();
    }

    @Test
    public void removeEmployee_CallsDeleteEmployee() {
        // Arrange
        Long id = 1L;

        // Act
        employeeController.removeEmployee(id);

        // Assert
        verify(employeeService, times(1)).deleteEmployee(id);
    }
    @Test
    public void addCareerPathToEmployee_ReturnsOkResponse() {
        // Arrange
        Long employeeId = 1L;
        Long careerPathId = 2L;
        Employee expectedEmployee = new Employee();

        when(employeeService.addCareerPathToEmployee(employeeId, careerPathId)).thenReturn(expectedEmployee);

        // Act
        ResponseEntity<Employee> response = employeeController.addCareerPathToEmployee(employeeId, careerPathId);

        // Assert
        assertEquals(ResponseEntity.ok(expectedEmployee), response);
        verify(employeeService, times(1)).addCareerPathToEmployee(employeeId, careerPathId);
    }

    @Test
    public void addSoftSkillCourseToEmployee_ReturnsCourseId() {
        // Arrange
        Long employeeId = 1L;
        Long courseId = 2L;
        Long expectedCourseId = 2L;

        when(employeeService.addSoftSkillCourseToEmployee(employeeId, courseId)).thenReturn(expectedCourseId);

        // Act
        Long result = employeeController.addSoftSkillCourseToEmployee(employeeId, courseId);

        // Assert
        assertEquals(expectedCourseId, result);
        verify(employeeService, times(1)).addSoftSkillCourseToEmployee(employeeId, courseId);
    }

    @Test
    public void addHardSkillCourseToEmployee_ReturnsCourseId() {
        // Arrange
        Long employeeId = 1L;
        Long courseId = 2L;
        Long expectedCourseId = 2L;

        when(employeeService.addSoftHardCourseToEmployee(employeeId, courseId)).thenReturn(expectedCourseId);

        // Act
        Long result = employeeController.addHardSkillCourseToEmployee(employeeId, courseId);

        // Assert
        assertEquals(expectedCourseId, result);
        verify(employeeService, times(1)).addSoftHardCourseToEmployee(employeeId, courseId);
    }

}