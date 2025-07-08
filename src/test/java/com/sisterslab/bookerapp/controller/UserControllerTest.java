package com.sisterslab.bookerapp.controller;

import com.sisterslab.bookerapp.model.dto.response.UserResponseDTO;
import com.sisterslab.bookerapp.model.entity.User;
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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    void getAllUsers_ReturnsUserList() throws Exception {
        // given
        List<UserResponseDTO> users = new ArrayList<>();

        // User1
        UserResponseDTO user1 = new UserResponseDTO();
        user1.setId(1L);
        user1.setUsername("user1");
        user1.setEmail("user1@example.com");

        // User2
        UserResponseDTO user2 = new UserResponseDTO();
        user2.setId(2L);
        user2.setUsername("user2");
        user2.setEmail("user2@example.com");

        users.add(user1);
        users.add(user2);

        when(userService.getAllUsers()).thenReturn(users);

        // when & then
        mockMvc.perform(get("/api/users"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].username").value("user1"))
                .andExpect(jsonPath("$[0].email").value("user1@example.com"))
                .andExpect(jsonPath("$[1].username").value("user2"))
                .andExpect(jsonPath("$[1].email").value("user2@example.com"));
    }

    @Test
    void getUserById_ReturnsUserById() throws Exception {
        // given
        User user = new User();
        user.setId(1L);
        user.setUsername("user1");
        user.setEmail("user1@example.com");


        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(1L);
        userResponseDTO.setUsername("user1");
        userResponseDTO.setEmail("user1@example.com");

        when(userService.getUserByIdDTO(1L)).thenReturn(userResponseDTO);

        // when & then
        mockMvc.perform(get("/api/users/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.username").value("user1"))
                .andExpect(jsonPath("$.email").value("user1@example.com"));
    }
}
