package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetCeoResponse {
    private Long id;
    private String name;
    private String email;
}
