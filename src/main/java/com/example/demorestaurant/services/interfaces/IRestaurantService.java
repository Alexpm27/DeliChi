package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.entities.Restaurant;

import java.util.List;

public interface IRestaurantService {
    BaseResponse create(CreateRestaurantRequest request, Long ceoId, Long zoneId);

    BaseResponse get(Long id);

    BaseResponse list();

    BaseResponse update(UpdateRestaurantRequest request, Long id, Long zoneId);

    BaseResponse delete(Long id);

    BaseResponse listAllRestaurantsByCeoId(Long ceoId);

    BaseResponse listAllRestaurantsByName(String name);

    BaseResponse listAllRestaurantsByZoneId(Long zoneId);

    List<Restaurant> listAllRestaurantsByCeoId2(Long ceoId);
}
