package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateRestaurantRequest {
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    private Long phoneNumber;
    @NotNull
    @NotBlank
    private String address;
    @NotNull
    @NotBlank
    private String schedule;
    @NotNull
    private Long zoneId;
    @NotNull
    @NotBlank
    private String kitchen;
    private String logo;
    private String banner;
}
