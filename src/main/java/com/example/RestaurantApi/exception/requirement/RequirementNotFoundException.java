package com.example.RestaurantApi.exception.requirement;

public class RequirementNotFoundException extends RuntimeException {

    public RequirementNotFoundException(String message) {
        super(message);

    }
}
