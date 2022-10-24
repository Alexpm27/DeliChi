package com.example.demorestaurant.entities.projections;

public interface RestaurantProjection {
    Long getCeoId();
    String getCeoName();
    Long getRestaurantId();
    String getRestaurantName();
    String getRestaurantAddress();
    Long getZoneId();
    String getZoneName();
    Long getRestaurantPhoneNumber();
    String getRestaurantKitchent();
    String getRestaurantSchedule();
}
