package com.example.demorestaurant.controllers;

import com.example.demorestaurant.services.interfaces.IZoneRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("zone-restaurant")
public class ZoneRestaurantController {

    @Autowired
    private IZoneRestaurantService service;
}
