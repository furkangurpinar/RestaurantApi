package com.example.RestaurantApi.controller;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDto> getUsers(){
        return userService.getUsers();
    }
}
