package com.example.demorestaurant.entities.projections;

public interface CommentProjection {
    Long getId();
    String getDate();
    String getContent();
    Integer getScore();
    Long getId_user();
    Long getId_restaurant();
}
