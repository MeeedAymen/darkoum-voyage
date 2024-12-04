package com.darkoum.darkoum.service.implementations;

import com.darkoum.darkoum.dtos.request.AuthDtoRequest;
import com.darkoum.darkoum.dtos.response.AuthDtoResponse;
import com.darkoum.darkoum.exeption.ResourceNotFoundException;
import com.darkoum.darkoum.model.User;
import com.darkoum.darkoum.model.UserRole;
import com.darkoum.darkoum.repository.UserRepository;
import com.darkoum.darkoum.service.interfaces.AuthServiceInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Register a new user and set role to AGENCY by default
    @Override
    public AuthDtoResponse register(AuthDtoRequest authDtoRequest) {
        if (userRepository.existsByEmail(authDtoRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }

        User user = new User();
        user.setEmail(authDtoRequest.getEmail());
        user.setPassword(passwordEncoder.encode(authDtoRequest.getPassword()));  // Encode password
        user.setPhoneNumber(authDtoRequest.getPhoneNumber());
        user.setRole(UserRole.AGENCY);  // Set the default role to AGENCY
        user.setActive(true);

        User savedUser = userRepository.save(user);

        return new AuthDtoResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getRole());
    }

    // User login - validate user
    @Override
    public AuthDtoResponse login(AuthDtoRequest authDtoRequest) {
        User user = userRepository.findByEmail(authDtoRequest.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User with email " + authDtoRequest.getEmail() + " not found"));

        if (!passwordEncoder.matches(authDtoRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid password.");
        }

        return new AuthDtoResponse(user.getId(), user.getEmail(), user.getRole());
    }
}
