package com.darkoum.darkoum.controller;

import com.darkoum.darkoum.dtos.request.AuthDtoRequest;
import com.darkoum.darkoum.dtos.response.AuthDtoResponse;
import com.darkoum.darkoum.service.interfaces.AuthServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthServiceInterface authService;

    // Register a new user
    @PostMapping("/register")
    public ResponseEntity<AuthDtoResponse> register(@RequestBody AuthDtoRequest authDtoRequest) {
        AuthDtoResponse response = authService.register(authDtoRequest);
        return ResponseEntity.ok(response);
    }

    // Login an existing user
    @PostMapping("/login")
    public ResponseEntity<AuthDtoResponse> login(@RequestBody AuthDtoRequest authDtoRequest) {
        AuthDtoResponse response = authService.login(authDtoRequest);
        return ResponseEntity.ok(response);
    }
}
