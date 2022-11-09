package com.example.demorestaurant.controllers.dtos.responses;

import com.example.demorestaurant.entities.Reservation;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class GetRestaurantResponse {
    private Long id;
    private String name;
    private String description;
    private Long phoneNumber;
    private String address;
    private String schedule;
    private String menu;
    private String kitchen;
    private Long zoneId;
    private Long CeoId;
    private List<CommentResponse> comments;
    private List<GetReservationResponse> reservations;
    private List<GetImageResponse> images;
}