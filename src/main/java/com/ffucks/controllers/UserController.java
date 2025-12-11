package com.ffucks.controllers;

import com.ffucks.dtos.CreateUserRequest;
import com.ffucks.dtos.UserResponse;
import com.ffucks.entities.User;
import com.ffucks.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public UserResponse create(@RequestBody CreateUserRequest createUserRequest) {
        return userService.createUserWithDevice(createUserRequest.getName(), createUserRequest.getSerial());
    }

    @DeleteMapping("/device/{id}")
    public void deleteDevice(@PathVariable Long id) {
        userService.removeDevice(id);
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
