package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter @Getter
public class CreateCommentRequest {
    private String date;
    private String content;
    private Integer score;
}
