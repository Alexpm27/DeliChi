package com.example.demorestaurant.controllers.dtos.responses;

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
    private GetZoneResponse zone;
    private CeoResponse ceo;
    private List<GetCommentResponse> comments;
    private List<GetReservationResponse> reservations;
    private List<GetImageResponse> images;
}