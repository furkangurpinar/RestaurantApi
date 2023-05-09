package com.example.RestaurantApi.exception.mealCategory;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MealCategoryErrorResponse {

    private int status;
    private String message;
    private long timeStamp;
}
