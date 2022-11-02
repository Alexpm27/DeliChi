package com.example.demorestaurant.entities.projections;

import java.util.List;

public interface RestaurantByResturantIdProyection {
    Long getId();
    String getLogo();
    String getBanner();
    String getName();
    String getAddress();
    Long getPhone_number();
    String getSchedule();
    String getKitchen();
    String getZone();
}
