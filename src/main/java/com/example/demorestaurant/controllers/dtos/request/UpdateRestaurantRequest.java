package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateRestaurantRequest {
    private String name;
    private String address;
    private String kitchen;
    private String logo;
    private String banner;
    private Long phone_number;
    private String schedule;
    private Long ceo_id;
}
