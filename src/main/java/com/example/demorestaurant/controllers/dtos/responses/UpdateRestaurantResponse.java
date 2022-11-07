package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRestaurantResponse {
    private Long id;
    private String name;
    private Long phoneNumber;
    private String address;
    private String schedule;
    private String kitchen;
    private String logo;
    private String banner;
    private String zone;
}
