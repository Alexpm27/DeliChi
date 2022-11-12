package com.example.demorestaurant.controllers.dtos.responses;

import com.example.demorestaurant.entities.Restaurant;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CeoResponse {
 private Long id;
 private String name;
 private String firstSurname;
 private String secondSurname;
 private String email;
 private Long phoneNumber;
}
