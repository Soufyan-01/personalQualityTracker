package com.example.personalQualityTracker.development.presentation;

import com.example.personalQualityTracker.development.application.EmployeeService;
import com.example.personalQualityTracker.development.application.StreamLeadService;
import com.example.personalQualityTracker.development.domain.*;
import com.example.personalQualityTracker.development.presentation.DTO.EmployeeDTO;
import com.example.personalQualityTracker.development.presentation.DTO.StreamLeadDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("http://localhost:3000/")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final StreamLeadService streamLeadService;




    public EmployeeController(EmployeeService employeeService, StreamLeadService streamLeadService) {
        this.employeeService = employeeService;
        this.streamLeadService = streamLeadService;
    }

    @PostMapping("/addEmployee")
    public void newEmployee(@RequestBody EmployeeDTO employeeDTO) throws IOException {
        employeeService.createNewEmployee(employeeDTO.name, employeeDTO.surname, employeeDTO.email, employeeDTO.function);
    }

    @PostMapping("/addStreamLead")
    public void newStreamLead(@RequestBody StreamLeadDTO employeeDTO) throws IOException {
        streamLeadService.createStreamLead(employeeDTO.name, employeeDTO.surname, employeeDTO.email, employeeDTO.function);
    }

    @GetMapping("/streamLead/{id}")
    public StreamLead getStreamLeadIdByEmail(@PathVariable("id") String email) {
        return streamLeadService.getStreamLeadIdByEmail(email);
    }

    @GetMapping("/employeeId/{id}")
    public Employee getEmployeeIdByEmail(@PathVariable("id") String email) {
        return employeeService.getEmployeeIdByEmail(email);
    }


    @GetMapping("/employee/{id}")
    public Employee getAssignmentById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }


    @DeleteMapping("/remove/{id}")
    public void removeEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employees")
    public List<Employee> employees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/updateEmployee/{id}")
    public Employee update(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO.image, employeeDTO.email);
    }

    @PostMapping("/addCareerPathToEmployee/{employeeId}/{careerPathId}")
    public ResponseEntity<Employee> addCareerPathToEmployee(@PathVariable Long employeeId, @PathVariable Long careerPathId) {
        Employee employee = employeeService.addCareerPathToEmployee(employeeId, careerPathId);

        return ResponseEntity.ok(employee);
    }

    @PostMapping("/addSoftSkillCourse/{employeeId}/{courseId}")
    public Long addSoftSkillCourseToEmployee(@PathVariable Long employeeId, @PathVariable Long courseId) {
        return employeeService.addSoftSkillCourseToEmployee(employeeId, courseId);
    }

    @PostMapping("/addHardSkillCourse/{employeeId}/{courseId}")
    public Long addHardSkillCourseToEmployee(@PathVariable Long employeeId, @PathVariable Long courseId) {
        return employeeService.addSoftHardCourseToEmployee(employeeId, courseId);
    }
}
