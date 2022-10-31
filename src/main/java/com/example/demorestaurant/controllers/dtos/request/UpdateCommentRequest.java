package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class UpdateCommentRequest {
    private Date date;
    private String content;
    private Integer score;
    private Long user_id;
    private Long restaurant_id;
}
