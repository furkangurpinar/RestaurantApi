package com.example.RestaurantApi.exception.requirement;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequirementRestExceptionHandler {

    @ExceptionHandler
    ResponseEntity<RequirementErrorResponse> handleException(RequirementNotFoundException exc) {
        RequirementErrorResponse error = new RequirementErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<RequirementErrorResponse> handleException(Exception exc) {
        RequirementErrorResponse error = new RequirementErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exc.getMessage(),
                System.currentTimeMillis());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
