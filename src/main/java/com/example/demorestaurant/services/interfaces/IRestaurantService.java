package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.CreateRestaurantResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetRestaurantResponse;
import com.example.demorestaurant.controllers.dtos.responses.UpdateRestaurantResponse;

public interface IRestaurantService {
    CreateRestaurantResponse create(CreateRestaurantRequest request);

    GetRestaurantResponse get(Long id);

    UpdateRestaurantResponse update(UpdateRestaurantRequest request, Long id);

    void delete(Long id);

    BaseResponse getRestaurantByRestaurantId(Long restaurantId);

    BaseResponse listAllRestaurantsByCeoId(Long ceoId);
}
