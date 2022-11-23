package com.example.RestaurantApi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto{

    private int userId;

    private String userName;

    private String userPhoneNumber;

    private String userMail;

    private String userPassword;

}
