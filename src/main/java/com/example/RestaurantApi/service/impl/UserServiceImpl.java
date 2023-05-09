package com.example.RestaurantApi.service.impl;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.repository.delegate.UserRepositoryDelegate;
import com.example.RestaurantApi.request.UserRequest;
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

    @Override
    public UserDto createUser(UserRequest request) {
        return userRepositoryDelegate.createUser(request);
    }

    @Override
    public void updateUser(int userId, UserRequest request) {
        UserDto user = userRepositoryDelegate.getUser(userId);

        final UserDto requestDto=request.getUserDto();

        user.setUserMail(requestDto.getUserMail());
        user.setUserName(requestDto.getUserName());
        user.setUserPhoneNumber(requestDto.getUserPhoneNumber());
        user.setUserPassword(requestDto.getUserPassword());

        userRepositoryDelegate.updateUser(user);
    }

    @Override
    public void deleteUser(int userId) {
        userRepositoryDelegate.deleteUser(userId);
    }
}
