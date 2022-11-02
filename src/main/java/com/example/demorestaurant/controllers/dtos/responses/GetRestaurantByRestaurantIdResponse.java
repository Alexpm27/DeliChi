package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Setter @Getter
public class GetRestaurantByRestaurantIdResponse {
    private Long id;
    private String logo;
    private String banner;
    private String name;
    private String address;
    private String schedule;
    private String kitchen;
    private Long phone_number;
    private String zone;
    private List<String> images;
    private List<CommentResponse> comments;
}
