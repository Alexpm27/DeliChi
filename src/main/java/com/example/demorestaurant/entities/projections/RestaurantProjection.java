package com.example.demorestaurant.entities.projections;

public interface RestaurantProjection {
    Long getId();
    String getName();
    Long getPhoneNumber();
    String getAddress();
    String getSchedule();
    String getKitchen();
    Long getZoneId();
    Long getCeoId();
    String getDescription();
    String getMenu();
}
