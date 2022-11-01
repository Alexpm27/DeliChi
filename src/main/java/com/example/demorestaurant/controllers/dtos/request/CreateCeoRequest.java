package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class CreateCeoRequest {
    private String name;
    private String first_surname;
    private String second_surname;
    private Long phone_number;
    private String email;
    private String password;
}
