//package com.example.personalQualityTracker.development.domain;
//
//import com.example.personalQualityTracker.development.domain.Enum.Function;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//public class PersonTest {
//    @Test
//    void getName() {
//        Person person = new Person("John", "Doe", Function.EMPLOYEE);
//        assertEquals("John", person.getName());
//    }
//
//    @Test
//    void getSurname() {
//        Person person = new Person("John", "Doe", Function.EMPLOYEE);
//        assertEquals("Doe", person.getSurname());
//    }
//
//    @Test
//    void getFuntion() {
//        Person person = new Person("John", "Doe", Function.EMPLOYEE);
//        assertEquals(Function.EMPLOYEE, person.getFunction());
//    }
//
//    @Test
//    void setId() {
//        Person person = new Person("John", "Doe", Function.EMPLOYEE);
//        person.setId(1L);
//        assertEquals(1L, person.getId());
//    }
//
//    @Test
//    void setName() {
//        Person person = new Person("John", "Doe", Function.EMPLOYEE);
//        person.setName("Jane");
//        assertEquals("Jane", person.getName());
//    }
//
//    @Test
//    void setSurname() {
//        Person person = new Person("John", "Doe", Function.STREAM_LEAD);
//        person.setSurname("Smith");
//        assertEquals("Smith", person.getSurname());
//    }
//
//    @Test
//    void setFuntion() {
//        Person person = new Person("John", "Doe", Function.EMPLOYEE);
//        person.setFunction(Function.STREAM_LEAD);
//        assertEquals(Function.STREAM_LEAD, person.getFunction());
//    }
//}
