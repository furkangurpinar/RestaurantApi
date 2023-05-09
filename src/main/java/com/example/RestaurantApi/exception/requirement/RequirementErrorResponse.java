package com.example.RestaurantApi.exception.requirement;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequirementErrorResponse {

    private int status;
    private String message;
    private long timeStamp;
}
