package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.GetCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Ceo;

public interface ICeoService {
    BaseResponse create(CreateCeoRequest request);

    GetCeoResponse get(Long id);

    BaseResponse get(String email);

    BaseResponse update(UpdateCeoRequest request, Long id);

    BaseResponse delete(Long id);

    Ceo FindAndEnsureExist(Long id);

    //BaseResponse validEmailAndPhoneNumber();
    //List<RestaurantResponse> listRestaurantsFromCeoId(Long ceoId);
}
