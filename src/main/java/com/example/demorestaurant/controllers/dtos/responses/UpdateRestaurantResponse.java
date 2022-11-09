package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateRestaurantResponse {
    private Long id;
    private String name;
    private Long phoneNumber;
    private String address;
    private String schedule;
    private String kitchen;
    private String description;
    private String menu;
    private String zone;
}
