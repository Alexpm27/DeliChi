package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Setter
@Getter
public class CreateReservationRequest {
    @NotNull
    private String date;
    private Integer people;
}
