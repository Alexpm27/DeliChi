package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class UpdateUserRequest {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String name;

    @NotBlank
    private String last_name;

    @NotBlank
    private Long phone_number;

    @NotBlank @NotNull
    private String password;

}
