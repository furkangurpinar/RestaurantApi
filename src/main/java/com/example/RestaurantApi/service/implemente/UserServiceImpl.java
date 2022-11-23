package com.example.RestaurantApi.service.implemente;

import com.example.RestaurantApi.entity.User;
import com.example.RestaurantApi.repository.UserRepository;
import com.example.RestaurantApi.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}
