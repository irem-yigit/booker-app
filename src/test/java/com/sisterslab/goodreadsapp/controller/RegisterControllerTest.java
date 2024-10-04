package com.sisterslab.goodreadsapp.controller;

import com.sisterslab.goodreadsapp.model.User;
import com.sisterslab.goodreadsapp.model.request.UserRequestDTO;
import com.sisterslab.goodreadsapp.service.UserService;
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
    void addUser_ReturnsCreatedUser() throws Exception {
        // given
        UserRequestDTO userRequestDTO = new UserRequestDTO();
        userRequestDTO.setName("John Doe");
        userRequestDTO.setEmail("john@example.com");
        userRequestDTO.setPassword("password123");
        userRequestDTO.setRole("USER");

        User user = new User();
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setRole(userRequestDTO.getRole());

        when(userService.addUser(any(User.class))).thenReturn(user);

        // when & then
        mockMvc.perform(post("/api/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\", \"email\":\"john@example.com\", \"password\":\"password123\", \"role\":\"USER\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"))
                .andExpect(jsonPath("$.email").value("john@example.com"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

}
