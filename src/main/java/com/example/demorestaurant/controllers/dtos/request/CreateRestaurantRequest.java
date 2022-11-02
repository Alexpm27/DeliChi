package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateRestaurantRequest {
    private String name;
    private String logo;
    private String banner;
    private String address;
    private String kitchen;
    private Long phone_number;
    private String schedule;
    private Long ceo_id;
    private Long zone_id;

}
