package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ListRestaurantsResponse {
    private Long id;
    private String logo;
    private String name;
    private String zone;
    private Integer score;
    private String description;
}
