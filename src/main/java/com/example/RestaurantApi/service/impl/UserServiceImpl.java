package com.example.RestaurantApi.service.impl;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.dto.converter.UserDtoConverter;
import com.example.RestaurantApi.repository.UserRepository;
import com.example.RestaurantApi.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserDtoConverter userDtoConverter;

    public UserServiceImpl(UserRepository userRepository, UserDtoConverter userDtoConverter) {
        this.userRepository = userRepository;
        this.userDtoConverter = userDtoConverter;
    }
    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDtoConverter::convert)
                .collect(Collectors.toList());
    }
}
