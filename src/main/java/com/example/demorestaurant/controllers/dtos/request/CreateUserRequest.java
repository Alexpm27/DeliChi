package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class CreateUserRequest {
    @Email
    @NotNull
    @NotBlank
    private String email;
    @NotNull
    @NotBlank
    private String name;
    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    private Long phoneNumber;
    @NotNull
    @NotBlank
    private String password;
}
