package com.sisterslab.bookerapp.controller;

import com.sisterslab.bookerapp.model.dto.response.UserResponseDTO;
import com.sisterslab.bookerapp.model.dto.request.UserRequestDTO;
import com.sisterslab.bookerapp.model.enums.UserRole;
import com.sisterslab.bookerapp.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class RegisterControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private RegisterController registerController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(registerController).build();
    }

    @Test
    void registerUser_ReturnsCreatedUser() throws Exception {
        // given
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setUsername("John Doe");
        userRequestDTO.setEmail("john@example.com");
        userRequestDTO.setPassword("password123");
        userRequestDTO.setRole(UserRole.valueOf("USER"));

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setUsername(userRequestDTO.getUsername());
        userResponseDTO.setEmail(userRequestDTO.getEmail());
        userResponseDTO.setRole(String.valueOf(userRequestDTO.getRole()));

        when(userService.registerUser(any(UserRequestDTO.class))).thenReturn(userResponseDTO);

        // when & then
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"John Doe\", \"email\":\"john@example.com\", \"password\":\"password123\", \"role\":\"USER\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.role").value("USER"));
    }
}
