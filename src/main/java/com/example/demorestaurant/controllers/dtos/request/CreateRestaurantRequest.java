package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateRestaurantRequest {
    @NotNull
    private String name;
    @NotNull
    private Long phoneNumber;
    @NotNull
    private String address;
    @NotNull
    private String schedule;
    private String description;
    private String menu;
    private String kitchen;
}
