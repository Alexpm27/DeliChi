package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetUserReservationsResponse {
    private String name;
    private String date;
    private Integer people;
}
