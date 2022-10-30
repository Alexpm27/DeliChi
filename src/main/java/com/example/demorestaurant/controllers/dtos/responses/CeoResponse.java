package com.example.demorestaurant.controllers.dtos.responses;

import com.example.demorestaurant.entities.Restaurant;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CeoResponse {
 private Long id;
 private String email;
 private String last_name;
 private String name;
 private Long phone_number;
}
