package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.services.interfaces.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {
    @Autowired
    private IRestaurantService service;

    @PostMapping("ceo/{ceoId}")
    public ResponseEntity<BaseResponse> create(@RequestBody CreateRestaurantRequest request, @PathVariable Long ceoId){
        BaseResponse baseResponse = service.create(request, ceoId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse>  get(@PathVariable Long id){
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurants/ceo/{ceoId}")
    public ResponseEntity<BaseResponse> listAllRestaurantsByCeoId(@PathVariable Long ceoId){
        BaseResponse baseResponse = service.listAllRestaurantsByCeoId(ceoId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurants/name/{name}")
    public ResponseEntity<BaseResponse> listAllRestaurantsByName(@PathVariable String name){
        BaseResponse baseResponse = service.listAllRestaurantsByName(name);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurant/{restaurantId}")
    public ResponseEntity<BaseResponse> getRestaurantByRestaurantId(@PathVariable Long restaurantId){
        BaseResponse baseResponse = service.getRestaurantByRestaurantId(restaurantId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@RequestBody UpdateRestaurantRequest request, @PathVariable Long id){
        BaseResponse baseResponse = service.update(request, id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
