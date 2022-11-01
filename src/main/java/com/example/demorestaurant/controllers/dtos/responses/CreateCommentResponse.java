package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class CreateCommentResponse {
    private Long id;
    private String date;
    private Integer score;
    private Long user_id;
    private String content;
    private Long restaurant_id;
}
