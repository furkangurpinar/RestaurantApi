package com.example.RestaurantApi.repository.delegate.impl;

import com.example.RestaurantApi.exception.user.UserNotFoundException;
import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.dto.converter.UserDtoConverter;
import com.example.RestaurantApi.model.entity.User;
import com.example.RestaurantApi.repository.UserRepository;
import com.example.RestaurantApi.repository.delegate.UserRepositoryDelegate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserRepositoryDelegateImpl implements UserRepositoryDelegate {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(UserDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUser(int userId) {
        Optional<User> response = userRepository.findById(userId);
        if (response.isEmpty()) {
            throw new UserNotFoundException("User ID Not Found - " + userId);
        }
        return UserDtoConverter.convert(response.get());
    }
}

