package com.example.personalQualityTracker.development.presentation.DTO;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDTOTest {


    @Test
    public void testEmployeeDTOFields() {
        // Create an instance of EmployeeDTO
        EmployeeDTO employeeDTO = new EmployeeDTO();

        // Create a sample Function object
        Function function = Function.EMPLOYEE;

        // Set the values in the EmployeeDTO
        employeeDTO.id = 1L;
        employeeDTO.image = "employee.jpg";
        employeeDTO.name = "John";
        employeeDTO.email = "john@example.com";
        employeeDTO.surname = "Doe";
        employeeDTO.function = function;
        employeeDTO.careerPath = 2L;

        // Verify that the fields in the EmployeeDTO are correctly set
        assertEquals(1L, employeeDTO.id);
        assertEquals("employee.jpg", employeeDTO.image);
        assertEquals("John", employeeDTO.name);
        assertEquals("john@example.com", employeeDTO.email);
        assertEquals("Doe", employeeDTO.surname);
        assertEquals(function, employeeDTO.function);
        assertEquals(2L, employeeDTO.careerPath);
    }

}