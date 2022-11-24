package com.example.RestaurantApi.service;

import com.example.RestaurantApi.model.dto.UserDto;
import java.util.List;

public interface UserService {

    List<UserDto> getUsers();
}
