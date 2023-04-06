package com.example.personalQualityTracker.security.data;


import com.example.personalQualityTracker.development.domain.Employee;
import com.example.personalQualityTracker.development.domain.Person;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * This is a data model.
 *
 * It is similar to a domain model, but is
 * intended for storage purposes. It does not
 * contain a lot of business logic.
 *
 * It implements UserDetails in order to make it usable
 * as login/registration model for Spring.
 */
@Entity
@Getter
@Setter
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;
    private String password;

    @OneToOne
    private Person person;

    private Positions positions;

    public User(){}

    public User(String username, String password, Positions positions) {
        this.username = username;
        this.password = password;
        this.positions = positions;
    }

    public void setEmployee(Employee employee) {
        if(positions != Positions.EMPLOYEE)
            throw new RuntimeException();
        this.person = employee;
    }

//    public void setStreamLead(StreamLead streamLead) {
//        if(positions != positions.EMPLOYEE)
//            throw new RuntimeException();
//        this.person = streamLead;
//    }
    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Positions getPersoonType() {
        return positions;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + positions));
    }

}