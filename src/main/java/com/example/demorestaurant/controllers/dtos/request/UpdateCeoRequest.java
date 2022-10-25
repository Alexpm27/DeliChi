package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UpdateCeoRequest {
    private String name;
    private String last_name;
    private Long phone_number;
    private String email;
    private String password;
}
