package com.example.demorestaurant.controllers.dtos.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class GetCeoRequest {
    @Email
    @NotNull
    private String email;
    @NotNull
    @NotBlank
    private String password;
}
