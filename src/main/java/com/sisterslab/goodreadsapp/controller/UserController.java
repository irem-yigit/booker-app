package com.sisterslab.goodreadsapp.controller;

import com.sisterslab.goodreadsapp.model.User;
import com.sisterslab.goodreadsapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Tüm kullanıcıları listeleme işlemi
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // Belirli bir kullanıcıyı ID ile getirme işlemi
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    //Kullanıcıyı id ile silme işlemi ve güncelleme işlemi ekleyebilirim
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) throws Exception {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
