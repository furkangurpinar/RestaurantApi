package com.example.RestaurantApi.model.dto.converter;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.entity.User;

public final class UserConverter {

    public static UserDto convert(User from) {
        return UserDto.builder()
                .userId(from.getUserId())
                .userName(from.getUserName())
                .userPhoneNumber(from.getUserPhoneNumber())
                .userMail(from.getUserMail())
                .userPassword(from.getUserPassword())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .build();
    }

    public static User convert(UserDto from) {
        return User.builder()
                .userId(from.getUserId())
                .userName(from.getUserName())
                .userPhoneNumber(from.getUserPhoneNumber())
                .userMail(from.getUserMail())
                .userPassword(from.getUserPassword())
                .createDate(from.getCreateDate())
                .updateDate(from.getUpdateDate())
                .build();
    }
}
