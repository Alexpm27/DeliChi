package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateRestaurantResponse {
    private Long id;
    private String name;
    private String description;
    private Long phoneNumber;
    private String address;
    private String schedule;
    private String menu;
    private String zone;
    private String kitchen;
}
