package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

        private Employee employee;

        @BeforeEach
        void setUp() {
            employee = new Employee();
            employee.setSoftSkillCourses(new ArrayList<>()); // Initialize the softSkillCourses list
            employee.setHardSkillCourses(new ArrayList<>()); // Initialize the hardSkillCourses list
        }

        @Test
        void addSoftSkillCourse() {
            // Create a soft skill course
            SoftSkillCourse softSkillCourse = new SoftSkillCourse("Cursus A", "Deze cursus gaat over A", 1, Arrays.asList(Interest.ARTISTIC, Interest.REALISTIC));

            // Add the soft skill course to the employee
            employee.addSoftSkillCourse(softSkillCourse);

            // Assert that the soft skill course is added to the employee's list
            Assertions.assertEquals(1, employee.getSoftSkillCourses().size());
            Assertions.assertEquals(softSkillCourse, employee.getSoftSkillCourses().get(0));
        }

        @Test
        void addHardSkillCourse() {
            // Create a hard skill course
            HardSkillCourse hardSkillCourse = new HardSkillCourse("Cursus B", "Deze cursus gaat over B", 2);

            // Add the hard skill course to the employee
            employee.addHardSkillCourse(hardSkillCourse);

            // Assert that the hard skill course is added to the employee's list
            Assertions.assertEquals(1, employee.getHardSkillCourses().size());
            Assertions.assertEquals(hardSkillCourse, employee.getHardSkillCourses().get(0));
        }


        @Test
        void isValid_ValidEmail_ReturnsTrue() {
            // Create an employee object
            Employee employee = new Employee();

            // Set a valid email
            String validEmail = "test@example.com";
            employee.setEmail(validEmail);

            // Invoke the isValid() method
            boolean isValid = employee.isValid(employee.getEmail());

            // Assert that the email is valid
            Assertions.assertTrue(isValid);
        }

        @Test
        void isValid_NullEmail_ReturnsFalse() {
            // Create an employee object
            Employee employee = new Employee();

            // Set a null email
            String nullEmail = null;
            employee.setEmail(nullEmail);

            // Invoke the isValid() method
            boolean isValid = employee.isValid(employee.getEmail());

            // Assert that the email is invalid
            Assertions.assertFalse(isValid);
        }

        @Test
        void isValid_InvalidEmail_ReturnsFalse() {
            // Create an employee object
            Employee employee = new Employee();

            // Set an invalid email
            String invalidEmail = "invalid-email";
            employee.setEmail(invalidEmail);

            // Invoke the isValid() method
            boolean isValid = employee.isValid(employee.getEmail());

            // Assert that the email is invalid
            Assertions.assertFalse(isValid);
        }


    @Test
    void getId() {
        // Set the id of the employee
        Long id = 1L;
        employee.setId(id);

        // Assert that the id is correctly retrieved
        Assertions.assertEquals(id, employee.getId());
    }

    @Test
    void getCareerPath() {
        // Create a career path
        CareerPath careerPath = new CareerPath();

        // Set the career path of the employee
        employee.setCareerPath(careerPath);

        // Assert that the career path is correctly retrieved
        Assertions.assertEquals(careerPath, employee.getCareerPath());
    }

    @Test
    void getEmail() {
        // Set the email of the employee
        String email = "employee@example.com";
        employee.setEmail(email);

        // Assert that the email is correctly retrieved
        Assertions.assertEquals(email, employee.getEmail());
    }

    @Test
    void employeeConstructor() {
        // Create test data
        String name = "John";
        String surname = "Doe";
        String loginEmail = "johndoe";
        String email = "johndoe@example.com";
        Function function = Function.EMPLOYEE;

        // Create an employee using the constructor
        Employee employee = new Employee(name, surname, loginEmail, email, function);

        // Assert that the fields are set correctly
        Assertions.assertEquals(name, employee.getName());
        Assertions.assertEquals(surname, employee.getSurname());
        Assertions.assertEquals(loginEmail, employee.getLoginEmail());
        Assertions.assertEquals(email, employee.getEmail());
        Assertions.assertEquals(function, employee.getFunction());
    }






        }