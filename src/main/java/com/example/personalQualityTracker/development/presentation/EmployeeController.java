package com.example.personalQualityTracker.development.presentation;

import com.example.personalQualityTracker.development.application.EmployeeService;
import com.example.personalQualityTracker.development.data.SpringEmployeeRepository;
import com.example.personalQualityTracker.development.domain.Employee;
import com.example.personalQualityTracker.development.presentation.DTO.EmployeeDTO;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@CrossOrigin("http://localhost:3000/")
public class EmployeeController {

    private final EmployeeService employeeService;

    private final SpringEmployeeRepository springEmployeeRepository;

    public EmployeeController(EmployeeService employeeService, SpringEmployeeRepository springEmployeeRepository) {
        this.employeeService = employeeService;
        this.springEmployeeRepository = springEmployeeRepository;
    }

    @PostMapping("/addEmployee")
    public Employee newEmployee(@RequestBody EmployeeDTO employeeDTO) throws IOException {
        return employeeService.createNewEmployee(employeeDTO.name, employeeDTO.email);
    }

    @GetMapping("/employee/{id}")
    public Employee getAssignmentById(@PathVariable("id") Long id) {
        return employeeService.getEmployeeById(id);
    }


    @DeleteMapping("/remove/{id}")
    public void removeEmployee(@PathVariable("id") Long id) {
        employeeService.deleteEmployee(id);
    }

    @GetMapping("/employee")
    public List<Employee> employees() {
        return employeeService.getAllEmployees();
    }

    @PutMapping("/updateEmployee/{id}")
    public Employee update(@PathVariable("id") Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployee(id, employeeDTO.image, employeeDTO.email);
    }
}
