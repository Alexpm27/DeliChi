package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UpdateReservationResponse {
    private Long id;
    private String date;
    private Integer people;
    private RestaurantResponse restaurant;
    private UserResponse user;
}
