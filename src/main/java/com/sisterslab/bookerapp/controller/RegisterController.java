package com.sisterslab.bookerapp.controller;

import com.sisterslab.bookerapp.model.dto.response.UserResponseDTO;
import com.sisterslab.bookerapp.model.entity.User;
import com.sisterslab.bookerapp.model.dto.request.UserRequestDTO;
import com.sisterslab.bookerapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class RegisterController {

    private final UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    //CREATE - Register user
    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> registerUser(@Valid @RequestBody UserRequestDTO userRequestDTO) {
        UserResponseDTO registeredUser = userService.registerUser(userRequestDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }
}
