package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CommentResponse {
    private String date;
    private Integer score;
    private String content;
}
