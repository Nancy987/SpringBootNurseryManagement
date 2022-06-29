package com.Prograd.Springboot.Backend.exceptions;

import com.Prograd.Springboot.Backend.Modals.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice              // make exception handler of controller
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomerNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(CustomerNotFound error) {
        String message = error.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PlantNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(PlantNotFound error) {
        String message = error.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(OrderNotFound.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(OrderNotFound error) {
        String message = error.getMessage();
        return new ResponseEntity<ApiResponse>(new ApiResponse(message, false), HttpStatus.NOT_FOUND);
    }
}
