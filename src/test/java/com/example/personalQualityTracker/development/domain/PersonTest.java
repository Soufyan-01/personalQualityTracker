package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.development.domain.Enum.Interest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    public void testConstructorAndGetterMethods() {
        // Create a new Person object
        Person person = new Person("John", "Doe", "johndoe@example.com", Function.EMPLOYEE);

        // Verify the name
        assertEquals("John", person.getName());

        // Verify the surname
        assertEquals("Doe", person.getSurname());

        // Verify the login email
        assertEquals("johndoe@example.com", person.getLoginEmail());

        // Verify the function
        assertEquals(Function.EMPLOYEE, person.getFunction());
    }


    @Test
    public void testInterestAndIdFields() {
        // Create a new Person object
        Person person = new Person();

        // Verify the default value of interest is null
        assertNull(person.getInterest());

        // Verify the default value of id is null
        assertNull(person.getId());

        // Set values for interest and id
        Interest interest = ( Interest.CONVENTIONAL);
        person.setInterest(interest);
        person.setId(1L);

        // Verify the updated values
        assertEquals(interest, person.getInterest());
        assertEquals(1L, person.getId());
    }
}