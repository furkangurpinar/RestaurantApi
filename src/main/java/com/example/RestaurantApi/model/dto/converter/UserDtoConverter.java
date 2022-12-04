package com.example.RestaurantApi.model.dto.converter;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.entity.User;

public final class UserDtoConverter {

    public static UserDto convert(User from) {
        return new UserDto(
                from.getUserId(),
                from.getUserName(),
                from.getUserPhoneNumber(),
                from.getUserMail(),
                from.getUserPassword());
    }

    public static User convert(UserDto from) {
        return User.builder()
                .userId(from.getUserId())
                .userName(from.getUserName())
                .userPhoneNumber(from.getUserPhoneNumber())
                .userMail(from.getUserMail())
                .userPassword(from.getUserPassword())
                .build();
    }
}
