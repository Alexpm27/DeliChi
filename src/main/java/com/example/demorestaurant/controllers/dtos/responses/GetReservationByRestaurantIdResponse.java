package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetReservationByRestaurantIdResponse {
    private String name;
    private String last_name;
    private String date;
    private Integer people;
}
