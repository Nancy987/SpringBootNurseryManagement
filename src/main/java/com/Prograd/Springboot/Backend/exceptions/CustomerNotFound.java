package com.Prograd.Springboot.Backend.exceptions;

public class CustomerNotFound extends Exception{
    public CustomerNotFound(String error) {
        super(error);
    }
}
