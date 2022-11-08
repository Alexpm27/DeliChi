package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRestaurantRequest {
    private String name;
    private String address;
    private String schedule;
    private String kitchen;
    private String phoneNumber;
    private String name_ceo;
    private String description;
    private String menu;
}
