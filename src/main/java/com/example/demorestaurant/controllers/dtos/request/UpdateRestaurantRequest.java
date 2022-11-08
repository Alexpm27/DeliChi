package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRestaurantRequest {
    private String name;
    private Long phoneNumber;
    private String address;
    private String schedule;
    private Long zoneId;
    private String kitchen;
    private String description;
    private String menu;
    private String logo;
    private String banner;
}
