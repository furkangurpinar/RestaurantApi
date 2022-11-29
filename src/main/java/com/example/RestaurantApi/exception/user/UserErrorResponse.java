package com.example.RestaurantApi.exception.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserErrorResponse {
    private int status;
    private String message;
    private long timeStamp;
}
