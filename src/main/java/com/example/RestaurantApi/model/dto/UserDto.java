package com.example.RestaurantApi.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int userId;

    private String userName;

    private String userPhoneNumber;

    private String userMail;

    private String userPassword;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;
}
