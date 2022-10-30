package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class CreateReservationRequest {
    private String date;
    private Integer people;
    private Long restaurant_id;
    private Long user_id;
}
