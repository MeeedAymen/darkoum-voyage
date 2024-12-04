package com.darkoum.darkoum.dtos.response;

import com.darkoum.darkoum.model.UserRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDtoResponse {

    private Long id;
    private String email;
    private UserRole role;  // Role assigned during registration

    public AuthDtoResponse(Long id, String email, UserRole role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
