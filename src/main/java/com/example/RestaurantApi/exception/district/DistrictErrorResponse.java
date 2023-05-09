package com.example.RestaurantApi.exception.district;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DistrictErrorResponse {

    private int status;
    private String message;
    private long timeStamp;
}
