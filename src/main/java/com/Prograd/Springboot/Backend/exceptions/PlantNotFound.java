package com.Prograd.Springboot.Backend.exceptions;

public class PlantNotFound extends Exception{
    public PlantNotFound(String error) {
        super(error);
    }
}
