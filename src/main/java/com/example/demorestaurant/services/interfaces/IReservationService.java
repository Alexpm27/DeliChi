package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateReservationRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateReservationRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetReservationResponse;
import com.example.demorestaurant.entities.Reservation;
import com.example.demorestaurant.entities.projections.ReservationProjection;

public interface IReservationService {
    BaseResponse create(CreateReservationRequest request);

    GetReservationResponse get(Long id);

    BaseResponse update(UpdateReservationRequest request, Long id);

    void delete(Long id);

    BaseResponse ListReservationByRestaurantId(Long restaurantId);

    Reservation fromReservationProjectionToReservation(ReservationProjection reservationProjection);
}
