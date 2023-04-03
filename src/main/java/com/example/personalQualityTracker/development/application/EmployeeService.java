package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringEmployeeRepository;
import com.example.personalQualityTracker.development.domain.Employee;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class EmployeeService {

    private final SpringEmployeeRepository springEmployeeRepository;


    public EmployeeService(SpringEmployeeRepository springEmployeeRepository) {
        this.springEmployeeRepository = springEmployeeRepository;
    }

    public Employee createNewEmployee(String name, String email) throws IOException {
        Employee employee = new Employee(name, email);

        if (employee.isValid(email)) {

            return springEmployeeRepository.save(employee);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Employee updateEmployee(Long id, String image, String email) {
        Employee employee = getEmployeeById(id);
//        employee.setImage(image);
        employee.setEmail(email);
        return springEmployeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return springEmployeeRepository.findById(id).get();
    }

//    public Employee getEmployeeAssignments(String email) {
//        return springEmployeeRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException(email));
//    }

    public void deleteEmployee(Long id) {
        springEmployeeRepository.deleteById(id);
    }

    public List<Employee> getAllEmployees() {
        return springEmployeeRepository.findAll();
    }

}