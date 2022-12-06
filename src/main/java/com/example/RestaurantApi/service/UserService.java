package com.example.RestaurantApi.service;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.request.UserRequest;

import java.util.List;

public interface UserService {

    List<UserDto> getUsers();

    UserDto getUser(int userId);

    UserDto createUser(UserRequest request);

    void deleteUser(int userId);
}
