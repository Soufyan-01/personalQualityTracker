package com.example.personalQualityTracker.development.application;

import com.example.personalQualityTracker.development.data.SpringStreamLeadRepository;
import com.example.personalQualityTracker.development.domain.Enum.Function;
import com.example.personalQualityTracker.development.domain.StreamLead;
import com.example.personalQualityTracker.security.application.UserService;
import com.example.personalQualityTracker.security.data.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


class StreamLeadServiceTest {

    @Mock
    private UserService userService;

    @Mock
    private SpringStreamLeadRepository springStreamLeadRepository;

    private StreamLeadService streamLeadService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        streamLeadService = new StreamLeadService(userService, springStreamLeadRepository);
    }

    @Test
    void createStreamLead_ExistingUser_ThrowsIllegalArgumentException() throws IOException {
        // Arrange
        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        Function function = Function.EMPLOYEE;

        User user = new User();
        user.setPerson(new StreamLead());
        when(userService.loadUserByUsername(email)).thenReturn(user);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () ->
                streamLeadService.createStreamLead(name, surname, email, function));
        verify(userService, times(1)).loadUserByUsername(email);
        verify(springStreamLeadRepository, never()).save(any(StreamLead.class));
    }

    @Test
    void getStreamLeadIdByEmail_ValidEmail_ReturnsStreamLead() {
        // Arrange
        String email = "john.doe@example.com";
        StreamLead streamLead = new StreamLead();
        when(springStreamLeadRepository.findByEmail(email)).thenReturn(Optional.of(streamLead));

        // Act
        StreamLead result = streamLeadService.getStreamLeadIdByEmail(email);

        // Assert
        assertEquals(streamLead, result);
        verify(springStreamLeadRepository, times(1)).findByEmail(email);
    }


    @Test
    void getStreamLeadIdByEmail_InvalidEmail_ThrowsUsernameNotFoundException() {
        // Arrange
        String email = "john.doe@example.com";
        when(springStreamLeadRepository.findByEmail(email)).thenReturn(java.util.Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () ->
                streamLeadService.getStreamLeadIdByEmail(email));
        verify(springStreamLeadRepository, times(1)).findByEmail(email);
    }

    @Test
    void getStreamLeadIdByEmail_ExistingEmail_ShouldReturnStreamLead() {
        // Arrange
        String email = "test@example.com";
        StreamLead streamLead = new StreamLead();
        streamLead.setId(1L);
        streamLead.setEmail(email);

        when(springStreamLeadRepository.findByEmail(email)).thenReturn(Optional.of(streamLead));

        // Act
        StreamLead result = streamLeadService.getStreamLeadIdByEmail(email);

        // Assert
        verify(springStreamLeadRepository, times(1)).findByEmail(email);
        assertEquals(streamLead, result);
    }

    @Test
    void getStreamLeadIdByEmail_NonExistingEmail_ShouldThrowException() {
        // Arrange
        String email = "nonexisting@example.com";

        when(springStreamLeadRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            streamLeadService.getStreamLeadIdByEmail(email);
        });
    }

}