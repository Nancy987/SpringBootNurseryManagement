package com.Prograd.Springboot.Backend.exceptions;

import com.Prograd.Springboot.Backend.Modals.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgsNotValidException(MethodArgumentNotValidException e){
        Map<String,String> resp = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach((error)->{
            String FieldName = ((FieldError)error).getField();   // type cast object 'error' in FieldError  & get field name that produces error.
            String message = error.getDefaultMessage();
            resp.put(FieldName,message);
        });
        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST);
    }
}
