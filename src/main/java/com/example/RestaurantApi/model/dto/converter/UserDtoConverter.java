package com.example.RestaurantApi.model.dto.converter;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserDtoConverter {

    public UserDto convert(User from) {
        return new UserDto(
                from.getUserId(),
                from.getUserName(),
                from.getUserPhoneNumber(),
                from.getUserMail(),
                from.getUserPassword());
    }
}
