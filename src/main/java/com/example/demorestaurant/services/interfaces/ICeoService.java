package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.GetCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.projections.RestaurantProjection;

import java.util.List;

public interface ICeoService {
    BaseResponse create(CreateCeoRequest request);

    GetCeoResponse get(Long id);

    BaseResponse get(GetCeoRequest request);

    BaseResponse update(UpdateCeoRequest request, Long id);

    BaseResponse delete(Long id);

    BaseResponse listAllRestaurantsByCeoId(Long ceoId);

    Ceo FindAndEnsureExist(Long id);

    BaseResponse validEmailAndPhoneNumber();
    //List<RestaurantResponse> listRestaurantsFromCeoId(Long ceoId);
}
