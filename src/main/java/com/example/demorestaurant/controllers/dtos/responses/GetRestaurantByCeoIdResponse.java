package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetRestaurantByCeoIdResponse {
    private Long id;
    private String logo;
    private String name;
    private String zone;
    private String description;
    private String menu;
}
