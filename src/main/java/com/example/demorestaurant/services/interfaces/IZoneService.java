package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetZoneResponse;
import com.example.demorestaurant.entities.Zone;

import java.util.List;

public interface IZoneService {
    BaseResponse get(Long id);
    BaseResponse ListAllZones();
    Zone FindAndEnsureExist(Long id);
}
