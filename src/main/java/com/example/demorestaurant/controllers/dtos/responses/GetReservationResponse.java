package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetReservationResponse {
    private Long id;
    private String date;
    private Integer people;
    private Long restaurant_id;
    private Long user_id;
}
