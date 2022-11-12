package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter @Builder
public class UpdateCommentResponse {
    private Long id;
    private String date;
    private Integer score;
    private String content;
    private UserResponse user;
    private RestaurantResponse restaurant;
}
