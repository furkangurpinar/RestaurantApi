package com.example.RestaurantApi.controller;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.dto.converter.UserDtoConverter;
import com.example.RestaurantApi.model.entity.User;
import com.example.RestaurantApi.request.UserRequest;
import com.example.RestaurantApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable int userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserRequest request) {
        User user = UserDtoConverter.convert(request.getUserDto());
        user.setCreateDate(LocalDateTime.now());
        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }
}
