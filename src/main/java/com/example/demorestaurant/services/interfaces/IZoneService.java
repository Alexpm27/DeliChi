package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetZoneResponse;

import java.util.List;

public interface IZoneService {
    BaseResponse get(Long id);
    BaseResponse ListAllZones();
}
