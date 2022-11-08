package com.example.demorestaurant.controllers.dtos.responses;

import com.example.demorestaurant.entities.Zone;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetRestaurantResponse {
    private Long id;
    private String banner;
    private String logo;
    private String name;
    private String address;
    private String schedule;
    private String kitchen;
    private Long phoneNumber;
    private Long zoneId;
    private Long CeoId;
    private String description;
    private String menu;
}
