package com.example.RestaurantApi;

import com.example.RestaurantApi.model.dto.UserDto;
import com.example.RestaurantApi.model.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtil {

    public static List<User> getMockUsers() {
        return new ArrayList<>(Arrays.asList(
                new User(1, "Elon", "05435031446", "haho@gmail.com", "12345", null, null),
                new User(2, "Mahmut", "123548", "halay@gmail.com", "54321", null, null),
                new User(3, "Ibrahim", "963258", "domdom@gmail.com", "34216", null, null)));
    }

    public static List<UserDto> getMockUsersDtos() {
        return new ArrayList<>(Arrays.asList(
                new UserDto(1, "Elon", "05435031446", "haho@gmail.com", "12345", null, null),
                new UserDto(2, "Mahmut", "123548", "halay@gmail.com", "54321", null, null),
                new UserDto(3, "Ibrahim", "963258", "domdom@gmail.com", "34216", null, null)));
    }
}
