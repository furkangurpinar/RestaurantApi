package com.example.RestaurantApi.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int userId;

    private String userName;

    private String userPhoneNumber;

    private String userMail;

    private String userPassword;

}
