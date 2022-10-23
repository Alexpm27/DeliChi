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
    private String phone_number;
    private String name_ceo;
}
