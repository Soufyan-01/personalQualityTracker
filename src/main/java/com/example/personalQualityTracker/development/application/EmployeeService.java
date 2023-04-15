package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringEmployeeRepository;
import com.example.personalQualityTracker.development.domain.Employee;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.security.application.UserService;
import com.example.personalQualityTracker.security.data.Positions;
import com.example.personalQualityTracker.security.data.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
//Test
    private final SpringEmployeeRepository springEmployeeRepository;

    private final UserService userService;

    public EmployeeService(SpringEmployeeRepository springEmployeeRepository, UserService userService) {
        this.springEmployeeRepository = springEmployeeRepository;
        this.userService = userService;
    }

    public Employee createNewEmployee(String name, String surname, String email, Function function) throws IOException {
        Employee employee = new Employee(name, surname, email, function);

        if (employee.isValid(email)) {

            if (userService.loadUserByUsername(email).getPositions() == Positions.STREAM_LEAD){
                employee.setFunction(Function.STREAM_LEAD);
            } else {
                employee.setFunction(Function.EMPLOYEE);
            }

            User user = userService.loadUserByUsername(email);

            if(user.getPerson() != null) {
                throw new IllegalArgumentException("This user already exists!");
            }
            user.setPerson(employee);

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