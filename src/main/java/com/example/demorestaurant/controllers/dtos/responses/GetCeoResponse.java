package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetCeoResponse {
    private Long id;
    private String name;
    private String first_surname;
    private String second_surname;
    private Long phone_number;
    private String email;
}
