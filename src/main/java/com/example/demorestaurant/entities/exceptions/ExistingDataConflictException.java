package com.example.demorestaurant.entities.exceptions;

public class ExistingDataConflictException extends RuntimeException{
    public ExistingDataConflictException(String message){
        super(message);
    }
}
