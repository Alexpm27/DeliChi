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

import javax.validation.Valid;

@RestController
@RequestMapping("restaurant")
public class RestaurantController {
    @Autowired
    private IRestaurantService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@PathVariable @Valid Long id){
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurants")
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse = service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurants/ceo/{ceoId}")
    public ResponseEntity<BaseResponse> listAllRestaurantsByCeoId(@PathVariable @Valid Long ceoId){
        BaseResponse baseResponse = service.listAllRestaurantsByCeoId(ceoId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurants/name/{name}")
    public ResponseEntity<BaseResponse> listAllRestaurantsByName(@PathVariable @Valid String name){
        BaseResponse baseResponse = service.listAllRestaurantsByName(name);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurants/zone/{zoneId}")
    public ResponseEntity<BaseResponse> listAllRestaurantsByZoneId(@PathVariable @Valid Long zoneId){
        BaseResponse baseResponse = service.listAllRestaurantsByZoneId(zoneId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("ceo/{ceoId}/zone/{zoneId}")
    public ResponseEntity<BaseResponse> create(@RequestBody @Valid CreateRestaurantRequest request,
                                               @PathVariable @Valid Long ceoId,
                                               @PathVariable @Valid Long zoneId){
        BaseResponse baseResponse = service.create(request, ceoId, zoneId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}/zone/{zoneId}")
    public ResponseEntity<BaseResponse> update(@RequestBody @Valid UpdateRestaurantRequest request,
                                               @PathVariable @Valid Long id,
                                               @PathVariable @Valid Long zoneId){
        BaseResponse baseResponse = service.update(request, id, zoneId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

}
