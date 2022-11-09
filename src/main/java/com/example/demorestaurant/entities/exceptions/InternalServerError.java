package com.example.demorestaurant.entities.exceptions;

public class InternalServerError extends RuntimeException{
    public InternalServerError(){
        super("Internal Server Error");
    }
}
