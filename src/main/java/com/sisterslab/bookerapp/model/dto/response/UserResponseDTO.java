package com.sisterslab.bookerapp.model.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {

    private Long id;
    private String username;
    private String email;
    private String role;
}
