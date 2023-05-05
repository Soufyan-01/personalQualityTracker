package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringEmployeeRepository;
import com.example.personalQualityTracker.development.data.SpringStreamLeadRepository;
import com.example.personalQualityTracker.development.domain.Employee;
import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.development.domain.StreamLead;
import com.example.personalQualityTracker.security.application.UserService;
import com.example.personalQualityTracker.security.data.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.io.IOException;

@Service
@Transactional
public class StreamLeadService {

    private final SpringEmployeeRepository springEmployeeRepository;
    private final UserService userService;

    private final SpringStreamLeadRepository springStreamLeadRepository;

    public StreamLeadService(SpringEmployeeRepository springEmployeeRepository, UserService userService, SpringStreamLeadRepository springStreamLeadRepository) {
        this.springEmployeeRepository = springEmployeeRepository;
        this.userService = userService;
        this.springStreamLeadRepository = springStreamLeadRepository;
    }

    public void createStreamLead(String name, String surname, String email, Function function) throws IOException {
        StreamLead employee = new StreamLead(name, surname, email, email, function);

        User user = userService.loadUserByUsername(email);

        if (employee.isValid(email)) {

            if(user.getPerson() != null) {
                throw new IllegalArgumentException("This user already exists!");
            }
            user.setStreamLead(employee);
            user.setPerson(employee);

            springStreamLeadRepository.save(employee);
        }
    }

    public StreamLead getStreamLeadById(Long id) {
        return springStreamLeadRepository.findById(id).get();
    }

    public StreamLead getStreamLeadIdByEmail(String email) {
        return springStreamLeadRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(email));
    }
}
