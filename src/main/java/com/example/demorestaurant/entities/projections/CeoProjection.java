package com.example.demorestaurant.entities.projections;

import com.example.demorestaurant.controllers.dtos.responses.RestaurantResponse;
import com.example.demorestaurant.entities.Restaurant;

import java.util.List;

public interface CeoProjection {
    Long getId();
    String getEmail();
    String getName();
    String getLast_name();
    Long getPhone_number();
    String getPasswword();
}
