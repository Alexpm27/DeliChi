package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.response.BaseResponse;
import com.example.demorestaurant.controllers.dtos.response.CreateCeoResponse;
import com.example.demorestaurant.controllers.dtos.response.GetCeoResponse;
import com.example.demorestaurant.controllers.dtos.response.UpdateCeoResponse;

public interface ICeoService {
    CreateCeoResponse create(CreateCeoRequest request);

    GetCeoResponse get(Long id);

    UpdateCeoResponse update(UpdateCeoRequest request, Long id);

    void delete(Long id);

    BaseResponse listAllRestaurantsByCeoId(Long ceoId);
}
