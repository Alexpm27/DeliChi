package com.example.demorestaurant.entities.projections;

public interface ReservationProjection {
    Long getId();
    String getDate();
    Integer getPeople();
    Long getRestaurantId();
    Long getUser_Id();

}
