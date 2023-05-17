package com.example.personalQualityTracker.security.data;

import com.example.personalQualityTracker.development.domain.Employee;
import com.example.personalQualityTracker.development.domain.StreamLead;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testSetEmployee() {
        User exampleClass = new User();
        Employee employee = new Employee();

        // Set positions to EMPLOYEE
        exampleClass.setPositions(Positions.EMPLOYEE);

        // Call setEmployee() and verify that the person is set as an employee
        exampleClass.setEmployee(employee);
        assertEquals(employee, exampleClass.getPerson());
    }

    @Test
    public void testSetStreamLead() {
        User exampleClass = new User();
        StreamLead streamLead = new StreamLead();

        // Set positions to STREAM_LEAD
        exampleClass.setPositions(Positions.STREAM_LEAD);

        // Call setStreamLead() and verify that the person is set as a stream lead
        exampleClass.setStreamLead(streamLead);
        assertEquals(streamLead, exampleClass.getPerson());
    }

    @Test
    public void testGetters() {
        User exampleClass = new User();
        exampleClass.setId(1L);
        exampleClass.setPassword("password");
        exampleClass.setUsername("user123");
        exampleClass.setPositions(Positions.EMPLOYEE);

        // Verify the values returned by the getters
        assertEquals(1L, exampleClass.getId());
        assertEquals("password", exampleClass.getPassword());
        assertEquals("user123", exampleClass.getUsername());
        assertEquals(Positions.EMPLOYEE, exampleClass.getPersoonType());
    }

    @Test
    public void testAuthority() {
        User exampleClass = new User();
        exampleClass.setPositions(Positions.EMPLOYEE);

        // Verify the authority returned by getAuthorities()
        assertEquals(List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE")), exampleClass.getAuthorities());
    }

    @Test
    public void testIsAccountNonExpired() {
        User exampleClass = new User();

        // Verify that isAccountNonExpired() returns true
        assertTrue(exampleClass.isAccountNonExpired());
    }

    @Test
    public void testIsAccountNonLocked() {
        User exampleClass = new User();

        // Verify that isAccountNonLocked() returns true
        assertTrue(exampleClass.isAccountNonLocked());
    }

    @Test
    public void testIsCredentialsNonExpired() {
        User exampleClass = new User();

        // Verify that isCredentialsNonExpired() returns true
        assertTrue(exampleClass.isCredentialsNonExpired());
    }

    @Test
    public void testIsEnabled() {
        User exampleClass = new User();

        // Verify that isEnabled() returns true
        assertTrue(exampleClass.isEnabled());
    }
}