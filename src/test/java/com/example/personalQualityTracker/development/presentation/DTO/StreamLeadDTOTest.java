package com.example.personalQualityTracker.development.presentation.DTO;

import com.example.personalQualityTracker.development.domain.Enum.Function;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StreamLeadDTOTest {

    @Test
    public void testStreamLeadDTOFields() {
        // Create an instance of StreamLeadDTO
        StreamLeadDTO streamLeadDTO = new StreamLeadDTO();

        // Create a sample Function object
        Function function = Function.EMPLOYEE;

        // Set the values in the StreamLeadDTO
        streamLeadDTO.id = 1L;
        streamLeadDTO.image = "lead.jpg";
        streamLeadDTO.name = "Alice";
        streamLeadDTO.email = "alice@example.com";
        streamLeadDTO.surname = "Smith";
        streamLeadDTO.function = function;

        // Verify that the fields in the StreamLeadDTO are correctly set
        assertEquals(1L, streamLeadDTO.id);
        assertEquals("lead.jpg", streamLeadDTO.image);
        assertEquals("Alice", streamLeadDTO.name);
        assertEquals("alice@example.com", streamLeadDTO.email);
        assertEquals("Smith", streamLeadDTO.surname);
        assertEquals(function, streamLeadDTO.function);
    }

}