package com.example.RestaurantApi.restController;

import com.example.RestaurantApi.entity.User;
import com.example.RestaurantApi.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRestController {

    private UserService userService;


    public UserRestController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/users")
    public List<User> getUsers(){
        return userService.getUsers();
    }

}
