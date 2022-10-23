package com.example.demorestaurant.controllers.dtos.response;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCeoResponse {
    private Long id;
    private String name;
    private String last_name;
    private Long phone_number;
    private String email;
    private String password;
}
