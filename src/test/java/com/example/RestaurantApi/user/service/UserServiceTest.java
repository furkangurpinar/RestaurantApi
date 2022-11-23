package com.example.RestaurantApi.user.service;

import com.example.RestaurantApi.TestUtil;
import com.example.RestaurantApi.entity.User;
import com.example.RestaurantApi.repository.UserRepository;
import com.example.RestaurantApi.service.implemente.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @MockBean
    private UserRepository mockRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void getUsers(){

        List<User> users = TestUtil.getMockUsers();

        when(mockRepository.findAll()).thenReturn(users);

        List<User> response=userService.getUsers();




    }


}