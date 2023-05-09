package com.example.RestaurantApi.exception.mealCategory;

public class MealCategoryNotFoundException extends RuntimeException{

    public MealCategoryNotFoundException(String message) {
        super(message);
    }
}
