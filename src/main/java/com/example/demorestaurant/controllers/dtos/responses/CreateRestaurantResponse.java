package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateRestaurantResponse {
    private Long id;
    private String name;
    private String address;
    private Long phoneNumber;
    private String schedule;
    private String kitchen;
    private String zone;
    private String description;
    private String menu;
}
