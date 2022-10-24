package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.response.BaseResponse;
import com.example.demorestaurant.controllers.dtos.response.CreateRestaurantResponse;
import com.example.demorestaurant.controllers.dtos.response.GetRestaurantResponse;
import com.example.demorestaurant.controllers.dtos.response.UpdateRestaurantResponse;
import com.example.demorestaurant.entities.projections.RestaurantProjection;

import java.util.List;

public interface IRestaurantService {
    CreateRestaurantResponse create(CreateRestaurantRequest request);
    GetRestaurantResponse get(Long id);
    UpdateRestaurantResponse update(UpdateRestaurantRequest request, Long id);
    void delete(Long id);
    BaseResponse getRestaurantByRestaurantId(Long restaurantId);
    BaseResponse listAllRestaurants();
}
