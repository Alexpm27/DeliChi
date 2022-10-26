package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.responses.RestaurantResponse;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.pivotes.ZoneRestaurant;
import com.example.demorestaurant.repositories.IZoneRestaurantRepository;
import com.example.demorestaurant.services.interfaces.IZoneRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ZoneRestaurantServiceImpl implements IZoneRestaurantService {
    @Autowired
    private IZoneRestaurantRepository repository;
}
