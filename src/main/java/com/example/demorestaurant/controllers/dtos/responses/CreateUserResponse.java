package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreateUserResponse {
    private Long id;
    private String email;
    private String name;
    private String lastName;
    private Long phoneNumber;
}
