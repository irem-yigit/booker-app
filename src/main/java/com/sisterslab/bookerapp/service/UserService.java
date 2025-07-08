package com.sisterslab.bookerapp.service;

import com.sisterslab.bookerapp.exception.ResourceNotFoundException;
import com.sisterslab.bookerapp.exception.ValidationException;
import com.sisterslab.bookerapp.model.dto.request.UserRequestDTO;
import com.sisterslab.bookerapp.model.dto.response.UserResponseDTO;
import com.sisterslab.bookerapp.model.entity.User;
import com.sisterslab.bookerapp.model.enums.UserRole;
import com.sisterslab.bookerapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Register
    public UserResponseDTO registerUser(UserRequestDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new ValidationException("There are users registered with this e-mail address.");
        }

        User user = new User();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setRole(dto.getRole() != null ? dto.getRole() : UserRole.USER);

        User savedUser = userRepository.save(user);
        return convertToResponseDTO(savedUser);
    }

    // List all users
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    // Get user by ID
    public UserResponseDTO getUserByIdDTO(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return convertToResponseDTO(user);
    }

    // Update user
    public UserResponseDTO updateUser(Long id, UserRequestDTO dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(new BCryptPasswordEncoder().encode(dto.getPassword()));
        user.setRole(dto.getRole());

        User updated = userRepository.save(user);
        return convertToResponseDTO(updated);
    }

    // Delete user
    public void deleteUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
        userRepository.delete(user);
    }

    //For Authentication - registration
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        if (username.contains("@")) {
            user = userRepository.findByEmail(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User with email " + username + " not found"));
        } else {
            user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new ResourceNotFoundException("User with username " + username + " not found"));
        }
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                getAuthorities(user)
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(User user) {
        return List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
    }

    // Manual DTO conversion
    private UserResponseDTO convertToResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole().name());
        return dto;
    }

}
