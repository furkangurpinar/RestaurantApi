package com.example.RestaurantApi.exception.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserRestExceptionHandler {

    @ExceptionHandler
    ResponseEntity<UserErrorResponse> handleException(UserNotFoundException exc) {
        UserErrorResponse error = new UserErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<UserErrorResponse> handleException(Exception exc) {
        UserErrorResponse error = new UserErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
