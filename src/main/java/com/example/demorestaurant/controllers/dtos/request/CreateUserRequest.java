package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter @Setter
public class CreateUserRequest {
    @Email
    private String email;
    private String name;
    private String last_name;
    private Long phone_number;
    private String password;
}
