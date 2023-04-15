package com.example.personalQualityTracker.development.domain;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @Test
    public void testConstructorAndGetters() {
        String name = "John";
        String surname = "Doe";
        Function function = Function.STREAM_LEAD;
        Person person = new Person(name, surname, function);

        Assertions.assertEquals(name, person.getName());
        Assertions.assertEquals(surname, person.getSurname());
        Assertions.assertEquals(function, person.getFuntion());
    }
    @Test
    public void testSetterAndGetters() {
        Person person = new Person();
        String name = "John";
        String surname = "Doe";
        Function function = Function.STREAM_LEAD;

        person.setName(name);
        person.setSurname(surname);
        person.setFuntion(function);

        Assertions.assertEquals(name, person.getName());
        Assertions.assertEquals(surname, person.getSurname());
        Assertions.assertEquals(function, person.getFuntion());
    }

}
