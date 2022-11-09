package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommentResponse {
    //pienso que es mejor el gt comment response
    private String date;
    private Integer score;
    private String content;
}
