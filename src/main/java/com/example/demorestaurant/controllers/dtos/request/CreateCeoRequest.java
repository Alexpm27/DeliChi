package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateCeoRequest {
    @NotNull
    @NotBlank
    private String name;
    private String firstSurname;
    private String secondSurname;
    @NotNull
    private Long phoneNumber;
    @NotNull
    @Email
    private String email;
    @NotNull
    @NotBlank
    private String password;
}
