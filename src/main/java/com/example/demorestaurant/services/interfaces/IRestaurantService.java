package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetImageResponse;
import com.example.demorestaurant.controllers.dtos.responses.RestaurantResponse;
import com.example.demorestaurant.entities.Restaurant;

import java.util.List;

public interface IRestaurantService {

    BaseResponse get(Long id);

    BaseResponse create(CreateRestaurantRequest request, Long ceoId, Long zoneId);

    BaseResponse list();

    BaseResponse listAllRestaurantsByName(String name);

    BaseResponse update(UpdateRestaurantRequest request, Long id, Long zoneId);

    BaseResponse delete(Long id);

    BaseResponse listAllImagesByRestaurantId(Long restaurantId);

    Restaurant findAndEnsureExist(Long id);

    RestaurantResponse fromRestaurantToRestaurantResponse(Restaurant restaurant);

    List<GetImageResponse> getImageResponsesList(Long id);

}
