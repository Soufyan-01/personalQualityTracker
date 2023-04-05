package com.example.personalQualityTracker.security.application;


import com.example.personalQualityTracker.security.data.Positions;
import com.example.personalQualityTracker.security.data.SpringUserRepository;
import com.example.personalQualityTracker.security.data.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 *  Implements UserDetailsService in order to make it usable
 *  as login/registration service for Spring.
 *  (see AuthenticationFilter)
 */
@Service
@Transactional
public class UserService implements UserDetailsService {

    private final SpringUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(SpringUserRepository repository, PasswordEncoder passwordEncoder) {
        this.userRepository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean login(String username, String password){
        User user = loadUserByUsername(username);
        return user.getPassword().equals(this.passwordEncoder.encode(password));
    }

    public void register(String username, String password, String positions) {
        String encodedPassword = this.passwordEncoder.encode(password);

        User user = new User(username, encodedPassword, Positions.valueOf(positions));

        this.userRepository.save(user);
    }

    @Override
    public User loadUserByUsername(String username) {
        return this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public List<User> geefAlle() {
        return userRepository.findAll();
    }

//    public Optional<User> findByPersoon(Employee employee) {
//        return userRepository.findByPersoon(employee);
//    }
}
