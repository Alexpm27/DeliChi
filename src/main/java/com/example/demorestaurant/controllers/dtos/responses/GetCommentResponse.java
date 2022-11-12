package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter @Getter @Builder
public class GetCommentResponse {
    private Long id;
    private String date;
    private Integer score;
    private String content;
    private UserResponse user;
    private RestaurantResponse restaurant;
}
