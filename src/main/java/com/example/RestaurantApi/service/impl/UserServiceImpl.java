package com.example.RestaurantApi.service.impl;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.repository.delegate.UserRepositoryDelegate;
import com.example.RestaurantApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepositoryDelegate userRepositoryDelegate;

    @Override
    public List<UserDto> getUsers() {
        return userRepositoryDelegate.getUsers();
    }

    @Override
    public UserDto getUser(int userId) {
        return userRepositoryDelegate.getUser(userId);
    }
}
