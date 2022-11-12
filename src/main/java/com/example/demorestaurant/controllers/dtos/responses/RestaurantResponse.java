package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class RestaurantResponse {
    private Long id;
    private String name;
    private List<GetImageResponse> image;
}
