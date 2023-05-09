package com.example.RestaurantApi.exception.mealCategory;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MealCategoryRestExceptionHandler {

    @ExceptionHandler
    ResponseEntity<MealCategoryErrorResponse> handleException(MealCategoryNotFoundException exc) {
        MealCategoryErrorResponse error = new MealCategoryErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<MealCategoryErrorResponse> handleException(Exception exc) {
        MealCategoryErrorResponse error = new MealCategoryErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
