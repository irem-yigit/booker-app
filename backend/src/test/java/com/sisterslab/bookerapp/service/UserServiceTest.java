package com.sisterslab.bookerapp.service;

import com.sisterslab.bookerapp.model.dto.request.UserRequestDTO;
import com.sisterslab.bookerapp.model.dto.response.UserResponseDTO;
import com.sisterslab.bookerapp.model.entity.User;
import com.sisterslab.bookerapp.model.enums.UserRole;
import com.sisterslab.bookerapp.repository.UserRepository;
import jakarta.validation.ValidationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void registerUser_UserAlreadyExists_ThrowsValidationException() {
        // Given
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("test@example.com");
        userRequestDTO.setPassword("password123");
        userRequestDTO.setRole(UserRole.valueOf("USER"));

        when(userRepository.existsByEmail(userRequestDTO.getEmail())).thenReturn(true);

        //When
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            userService.registerUser(userRequestDTO);
        });

        //Then
        assertEquals("Bu email ile kayıtlı kullanıcı bulunmaktadır.", exception.getMessage());
        verify(userRepository, never()).save(any());
    }

    @Test
    public void registerUser_NewUser_SavesUserAndReturnsDTO() {
        // Given
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setEmail("test@example.com");
        userRequestDTO.setPassword("password123");
        userRequestDTO.setRole(UserRole.valueOf("USER"));

        User user = new User();
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setRole(String.valueOf(user.getRole()));

        when(userRepository.existsByEmail(userRequestDTO.getEmail())).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn(user);

        // When
        UserResponseDTO registeredUser = userService.registerUser(userRequestDTO);

        // Then
        assertEquals(userRequestDTO.getEmail(), registeredUser.getEmail());
        assertNotNull(registeredUser.getUsername());
        assertEquals(userRequestDTO.getRole(), registeredUser.getRole());
        verify(userRepository).save(any(User.class));
    }

    @Test
    public void getAllUsers_ReturnsUserList() {
        // Given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole(UserRole.valueOf("USER"));

        when(userRepository.findAll()).thenReturn(List.of(user));

        // When
        List<UserResponseDTO> users = userService.getAllUsers();

        // Then
        assertEquals(1, users.size());
        assertEquals(user.getEmail(), users.get(0).getEmail());
        verify(userRepository).findAll();
    }

    @Test
    public void getUserById_UserExists_ReturnsUser() {
        // Given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole(UserRole.valueOf("USER"));

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        // When
        UserResponseDTO foundUser = userService.getUserByIdDTO(1L);

        // Then
        assertEquals(user.getEmail(), foundUser.getEmail());
        verify(userRepository).findById(1L);
    }

    @Test
    public void getUserById_UserDoesNotExist_ThrowsException() {
        //Given
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        //When
        assertThrows(NoSuchElementException.class, () -> {
            userService.getUserByIdDTO(1L);
        });

        //Then
        verify(userRepository).findById(1L);
    }

    @Test
    public void loadUserByUsername_UserExists_ReturnsUserDetails() {
        //Given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole(UserRole.valueOf("USER"));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));

        //When
        UserDetails userDetails = userService.loadUserByUsername(user.getEmail());

        //Then
        assertEquals(user.getEmail(), userDetails.getUsername());
        assertNotNull(userDetails.getPassword());
        assertTrue(userDetails.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals(user.getRole())));
        verify(userRepository).findByEmail(user.getEmail());
    }

    @Test
    public void loadUserByUsername_UserDoesNotExist_ThrowsUsernameNotFoundException() {
        //Given
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole(UserRole.valueOf("USER"));

        when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.empty());

        //When
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername(user.getEmail());
        });

        //Then
        verify(userRepository).findByEmail(user.getEmail());
    }

}
