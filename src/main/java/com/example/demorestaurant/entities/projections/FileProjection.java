package com.example.demorestaurant.entities.projections;


public interface FileProjection {
    Long getId();
    String getFileUrl();
    String getName();
    String getImageType();
    Long getIdRestaurant();
}
