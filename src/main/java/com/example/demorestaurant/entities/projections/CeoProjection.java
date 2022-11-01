package com.example.demorestaurant.entities.projections;

import com.example.demorestaurant.controllers.dtos.responses.RestaurantResponse;
import com.example.demorestaurant.entities.Restaurant;

import java.util.List;

public interface CeoProjection {
    Long getId();
    String getEmail();
    String getName();
    String getFirst_surname();
    String getSecond_surname();
    Long getPhone_number();
    String getPasswword();
}
