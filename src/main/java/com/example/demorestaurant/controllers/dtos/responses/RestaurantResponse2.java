package com.example.demorestaurant.controllers.dtos.responses;

import com.example.demorestaurant.entities.Ceo;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RestaurantResponse2 {
    private Long Id;
    private String Name;
    private String Address;
    private Long PhoneNumber;
    private String Kitchent;
    private String Schedule;
    private Long ceoId;
    private String ceoName;
    private String ceoFirst_surname;
    private String ceoSecond_surname;
    private String ceoEmail;
}
