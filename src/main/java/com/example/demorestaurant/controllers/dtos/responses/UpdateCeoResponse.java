package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class UpdateCeoResponse {
    private Long id;
    private String name;
    private String firstSurname;
    private String secondSurname;
    private Long phoneNumber;
    private String email;
}
