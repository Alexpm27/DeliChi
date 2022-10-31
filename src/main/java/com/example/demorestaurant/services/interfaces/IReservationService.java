package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateReservationRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateReservationRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetReservationResponse;

public interface IReservationService {
    BaseResponse create(CreateReservationRequest request);

    GetReservationResponse get(Long id);

    BaseResponse update(UpdateReservationRequest request, Long id);

    void delete(Long id);

    BaseResponse ListReservationByRestaurantId(Long restaurantId);
}
