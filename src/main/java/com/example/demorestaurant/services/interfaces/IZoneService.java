package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetZoneResponse;
import com.example.demorestaurant.entities.Zone;

import java.util.List;

public interface IZoneService {
    BaseResponse get(Long id);

    BaseResponse list();

    Zone findAndEnsureExist(Long id);

    GetZoneResponse from(Zone zone);

    BaseResponse listAllRestaurantByZoneId(Long id);

}
