package com.example.RestaurantApi.exception.Province;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProvinceErrorResponse {

    private int status;
    private String message;
    private long timeStamp;

}
