package com.example.demorestaurant.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRestaurantResponse {
    private String name;
    private String address;
    private String schedule;
    private String kitchen;
    private String phone_number;
    private String name_ceo;
}
