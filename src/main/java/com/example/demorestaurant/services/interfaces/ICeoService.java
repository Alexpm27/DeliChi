package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.GetCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Ceo;

public interface ICeoService {

    BaseResponse get(String email);

    BaseResponse listAllRestaurantByCeoId(Long id);

    BaseResponse create(CreateCeoRequest request);

    BaseResponse update(UpdateCeoRequest request, Long id);

    BaseResponse delete(Long id);

    Ceo findAndEnsureExist(Long id);

    CeoResponse fromCeoToCeoResponse(Ceo ceo);

}
