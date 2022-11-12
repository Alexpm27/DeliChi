package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.services.interfaces.IZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("zone")
public class ZoneController {
    @Autowired
    private IZoneService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@Valid @PathVariable Long id){
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("zones")
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse = service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}/restaurants")
    public ResponseEntity<BaseResponse> listAllRestaurantByZoneId(@Valid @PathVariable Long id){
        BaseResponse baseResponse = service.listAllRestaurantByZoneId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}