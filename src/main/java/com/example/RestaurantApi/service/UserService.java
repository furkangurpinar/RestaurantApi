package com.example.RestaurantApi.service;

import com.example.RestaurantApi.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserService {


    List<User> getUsers();
}
