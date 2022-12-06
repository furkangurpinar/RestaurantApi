package com.example.RestaurantApi.repository.delegate.impl;

import com.example.RestaurantApi.exception.user.UserNotFoundException;
import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.dto.converter.UserConverter;
import com.example.RestaurantApi.model.entity.User;
import com.example.RestaurantApi.repository.UserRepository;
import com.example.RestaurantApi.repository.delegate.UserRepositoryDelegate;
import com.example.RestaurantApi.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
                .map(UserConverter::convert)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUser(int userId) {
        Optional<User> response = userRepository.findById(userId);
        if (response.isEmpty()) {
            throw new UserNotFoundException("User ID Not Found - " + userId);
        }
        return UserConverter.convert(response.get());
    }

    @Transactional
    @Override
    public UserDto createUser(UserRequest request) {
        User user = UserConverter.convert(request.getUserDto());
        user.setCreateDate(LocalDateTime.now());
        userRepository.save(user);
        return UserConverter.convert(user);
    }

    @Transactional
    @Override
    public void deleteUser(int userId) {
        Optional<User> response = userRepository.findById(userId);
        if(response.isEmpty()) {
            throw new UserNotFoundException("User ID Not Found - " + userId);
        }
        userRepository.delete(response.get());
    }
}

