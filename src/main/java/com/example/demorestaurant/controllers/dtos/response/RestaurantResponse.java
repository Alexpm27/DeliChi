package com.example.demorestaurant.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class RestaurantResponse {
    private Long ceoId;
    private String ceoName;
    private Long restaurantId;
    private String restaurantName;
    private String restaurantAddress;
    private Long zoneId;
    private String zoneName;
    private Long restaurantPhoneNumber;
    private String restaurantKitchent;
    private String restaurantSchedule;
}
