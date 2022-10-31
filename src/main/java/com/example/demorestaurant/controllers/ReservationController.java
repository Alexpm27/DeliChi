package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.request.CreateReservationRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateReservationRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetReservationResponse;
import com.example.demorestaurant.services.interfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("reservation")
public class ReservationController {
    @Autowired
    private IReservationService service;

    @PostMapping
    public ResponseEntity<BaseResponse> create(@RequestBody CreateReservationRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public GetReservationResponse get(@PathVariable Long id){
        return service.get(id);
    }

    @GetMapping("reservations/restaurant/{restaurantId}")
    public ResponseEntity<BaseResponse> ListReservationByRestaurantId(@PathVariable Long restaurantId){
        BaseResponse baseResponse = service.ListReservationByRestaurantId(restaurantId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@RequestBody UpdateReservationRequest request, @PathVariable Long id){
        BaseResponse baseResponse = service.update(request, id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }
}
