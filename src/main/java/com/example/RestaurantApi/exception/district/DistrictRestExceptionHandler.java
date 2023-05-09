package com.example.RestaurantApi.exception.district;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DistrictRestExceptionHandler {

    @ExceptionHandler
    ResponseEntity<DistrictErrorResponse> handleException(DistrictNotFoundException exc) {
        DistrictErrorResponse error = new DistrictErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<DistrictErrorResponse> handleException(Exception exc) {
        DistrictErrorResponse error = new DistrictErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
