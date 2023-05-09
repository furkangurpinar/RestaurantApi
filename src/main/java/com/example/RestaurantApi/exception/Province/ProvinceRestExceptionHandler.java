package com.example.RestaurantApi.exception.Province;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProvinceRestExceptionHandler {

    @ExceptionHandler
    ResponseEntity<ProvinceErrorResponse> handleException(ProvinceNotFoundException exc) {
        ProvinceErrorResponse error = new ProvinceErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<ProvinceErrorResponse> handleException(Exception exc) {
        ProvinceErrorResponse error = new ProvinceErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
