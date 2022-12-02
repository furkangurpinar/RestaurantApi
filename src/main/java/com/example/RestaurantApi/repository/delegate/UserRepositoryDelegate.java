package com.example.RestaurantApi.repository.delegate;

import com.example.RestaurantApi.model.dto.UserDto;

import java.util.List;

public interface UserRepositoryDelegate {
    List<UserDto> getUsers();

    UserDto getUser(int userId);
}
