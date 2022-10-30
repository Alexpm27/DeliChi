package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateReservationRequest {
    private String date;
    private Integer people;
}
