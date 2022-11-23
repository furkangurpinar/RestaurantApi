package com.example.RestaurantApi.dto.converter;

import com.example.RestaurantApi.dto.UserDto;
import com.example.RestaurantApi.entity.User;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convertToUserDto(User from) {
        return new UserDto(
                from.getUserId(),
                from.getUserName(),
                from.getUserPhoneNumber(),
                from.getUserMail(),
                from.getUserPassword());


    }
}
