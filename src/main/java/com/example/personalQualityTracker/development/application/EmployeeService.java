package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringEmployeeRepository;
import com.example.personalQualityTracker.development.data.SpringStreamLeadRepository;
import com.example.personalQualityTracker.development.domain.Employee;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.development.domain.StreamLead;
import com.example.personalQualityTracker.security.application.UserService;
import com.example.personalQualityTracker.security.data.Positions;
import com.example.personalQualityTracker.security.data.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
@Transactional
public class EmployeeService {
//Test
    private final SpringEmployeeRepository springEmployeeRepository;

    private final UserService userService;

    private final SpringStreamLeadRepository springStreamLeadRepository;

    public EmployeeService(SpringEmployeeRepository springEmployeeRepository, UserService userService, SpringStreamLeadRepository springStreamLeadRepository) {
        this.springEmployeeRepository = springEmployeeRepository;
        this.userService = userService;
        this.springStreamLeadRepository = springStreamLeadRepository;
    }

    public void createNewEmployee(String name, String surname, String email, Function function) throws IOException {
        Employee employee = new Employee(name, surname, email, function);

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
//        employee.setImage(image);
        employee.setEmail(email);
        return springEmployeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id) {
        return springEmployeeRepository.findById(id).get();
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

}