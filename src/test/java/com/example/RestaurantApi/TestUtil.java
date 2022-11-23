package com.example.RestaurantApi;

import com.example.RestaurantApi.entity.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestUtil {
    public static List<User> getMockUsers(){
        return new ArrayList<>(Arrays.asList(
                new User(1, "Elon", "05435031446", "haho@gmail.com", "12345"),
                new User(2, "Mahmut", "123548", "halay@gmail.com", "54321"),
                new User(3, "İbrahim", "963258", "domdom@gmail.com", "34216")));
    }
}
