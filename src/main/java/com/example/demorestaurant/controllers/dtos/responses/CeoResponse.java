package com.example.demorestaurant.controllers.dtos.responses;

import com.example.demorestaurant.entities.Restaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CeoResponse {
 private Long id;
 private String name;
 private String first_surname;
 private String second_surname;
 private String email;
 private Long phone_number;
}
