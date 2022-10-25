package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateUserResponse {
    private Long id;
    private String email;
    private String name;
    private String last_name;
    private Long phone_number;
}
