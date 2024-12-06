package com.darkoum.darkoum.controller;


import com.darkoum.darkoum.dtos.request.UserDtoRequest;
import com.darkoum.darkoum.dtos.response.UserDtoResponse;
import com.darkoum.darkoum.service.interfaces.UserServiceInterface;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/users")
@Validated
public class UserController {
    private final UserServiceInterface userService;

    @PostMapping
    public UserDtoResponse create(@RequestBody @Valid UserDtoRequest user) {
        return userService.create(user);
    }

    @PutMapping("{id}")
    public UserDtoResponse update(@RequestBody @Valid UserDtoRequest user, @PathVariable Long id) {
        return userService.update(user, id);
    }

    @GetMapping
    public List<UserDtoResponse> getAll() {
        return userService.findAll();
    }

    @GetMapping("{id}")
    public UserDtoResponse getUser(@PathVariable Long id) {
        return userService.findUserById(id);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }
}