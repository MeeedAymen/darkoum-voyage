package com.darkoum.darkoum.service.implementations;

import com.darkoum.darkoum.dtos.request.UserDtoRequest;
import com.darkoum.darkoum.dtos.response.UserDtoResponse;
import com.darkoum.darkoum.exeption.ResourceNotFoundException;
import com.darkoum.darkoum.model.User;
import com.darkoum.darkoum.model.UserRole;
import com.darkoum.darkoum.repository.UserRepository;
import com.darkoum.darkoum.service.interfaces.UserServiceInterface;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService implements UserServiceInterface {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserDtoResponse create(UserDtoRequest userRequest) {
        if (userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }
        User user = modelMapper.map(userRequest, User.class);
        user.setActive(true);
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser, UserDtoResponse.class);
    }

    public UserDtoResponse update(UserDtoRequest userRequest, Long id) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));

        if (!existingUser.getEmail().equals(userRequest.getEmail()) &&
                userRepository.existsByEmail(userRequest.getEmail())) {
            throw new IllegalArgumentException("Email already in use.");
        }

        existingUser.setName(userRequest.getName());
        existingUser.setEmail(userRequest.getEmail());
        existingUser.setPassword(userRequest.getPassword());
        existingUser.setPhoneNumber(userRequest.getPhoneNumber());
        existingUser.setRole(UserRole.valueOf(userRequest.getRole().toUpperCase()));
        existingUser.setActive(userRequest.isActive());

        User updatedUser = userRepository.save(existingUser);
        return modelMapper.map(updatedUser, UserDtoResponse.class);
    }

    public List<UserDtoResponse> findAll() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UserDtoResponse.class))
                .collect(Collectors.toList());
    }

    public UserDtoResponse findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        return modelMapper.map(user, UserDtoResponse.class);
    }

    public void delete(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with id " + id + " not found"));
        userRepository.delete(user);
    }

}