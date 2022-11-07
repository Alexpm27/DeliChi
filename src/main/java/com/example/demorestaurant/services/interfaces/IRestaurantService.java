package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.CreateRestaurantResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetRestaurantResponse;
import com.example.demorestaurant.controllers.dtos.responses.UpdateRestaurantResponse;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.projections.RestaurantProjection;

import java.util.List;

public interface IRestaurantService {
    BaseResponse create(CreateRestaurantRequest request, Long ceoId);

    BaseResponse get(Long id);

    BaseResponse update(UpdateRestaurantRequest request, Long id);

    BaseResponse delete(Long id);

    BaseResponse getRestaurantByRestaurantId(Long restaurantId);

    BaseResponse listAllRestaurantsByCeoId(Long ceoId);

    BaseResponse listAllRestaurantsByName(String name);

    List<Restaurant> listAllRestaurantsByCeoId2(Long ceoId);
}
