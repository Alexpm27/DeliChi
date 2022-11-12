package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateReservationRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateReservationRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetReservationResponse;
import com.example.demorestaurant.controllers.dtos.responses.ReservationResponse;
import com.example.demorestaurant.entities.Reservation;
import com.example.demorestaurant.entities.projections.ReservationProjection;
import org.springframework.web.multipart.MultipartFile;

public interface IReservationService {
    BaseResponse create(CreateReservationRequest request, Long userId, Long restaurantId);

    BaseResponse get(Long id);

    BaseResponse update(UpdateReservationRequest request, Long id);

    BaseResponse delete(Long id);

    ReservationResponse fromReservationToReservationResponse(Reservation reservation);

}
