package com.example.demorestaurant.entities.exceptions;

public class NotFoundException extends RuntimeException{
    public NotFoundException() {
        super("Data Not Found");
    }
}
