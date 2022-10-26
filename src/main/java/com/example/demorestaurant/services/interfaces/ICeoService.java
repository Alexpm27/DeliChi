package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.CreateCeoResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetCeoResponse;
import com.example.demorestaurant.controllers.dtos.responses.UpdateCeoResponse;
import com.example.demorestaurant.entities.Ceo;

public interface ICeoService {
    BaseResponse create(CreateCeoRequest request);

    GetCeoResponse get(Long id);

    BaseResponse update(UpdateCeoRequest request, Long id);

    void delete(Long id);

    BaseResponse listAllRestaurantsByCeoId(Long ceoId);

    Ceo FindAndEnsureExist(Long id);
}
