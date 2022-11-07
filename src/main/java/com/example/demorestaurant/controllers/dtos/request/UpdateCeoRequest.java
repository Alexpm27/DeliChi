package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class UpdateCeoRequest {
    private String name;
    private String firstSurname;
    private String secondSurname;
    private Long phoneNumber;
    @Email
    private String email;
    private String password;
}
