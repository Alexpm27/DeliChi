package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetReservationByRestaurantIdResponse {
    private Long id;
    private String date;
    private Integer people;
    private Long restaurantId;
    private Long userId;
}
