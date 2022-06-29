package com.Prograd.Springboot.Backend.exceptions;

public class OrderNotFound extends Exception{
    public OrderNotFound(String error) {
        super(error);
    }
}
