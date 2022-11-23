package com.example.RestaurantApi.service;

import com.example.RestaurantApi.dto.UserDto;
import com.example.RestaurantApi.entity.User;
import java.util.List;

public interface UserService {

    List<UserDto> getUsers();
}
