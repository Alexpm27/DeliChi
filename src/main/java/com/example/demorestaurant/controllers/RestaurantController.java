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
    public ResponseEntity<BaseResponse> get(@Valid @PathVariable Long id){
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurants")
    public ResponseEntity<BaseResponse> list(){
        BaseResponse baseResponse = service.list();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurants/name/{name}")
    public ResponseEntity<BaseResponse> listAllRestaurantsByName(@Valid @PathVariable String name){
        BaseResponse baseResponse = service.listAllRestaurantsByName(name);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("ceo/{ceoId}/zone/{zoneId}")
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateRestaurantRequest request,
                                               @Valid @PathVariable Long ceoId,
                                               @Valid @PathVariable Long zoneId){
        BaseResponse baseResponse = service.create(request, ceoId, zoneId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}/zone/{zoneId}")
    public ResponseEntity<BaseResponse> update(@Valid @RequestBody  UpdateRestaurantRequest request,
                                               @Valid @PathVariable Long id,
                                               @Valid @PathVariable Long zoneId){
        BaseResponse baseResponse = service.update(request, id, zoneId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> delete(@Valid @PathVariable Long id){
        BaseResponse baseResponse = service.delete(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    /*
    @PostMapping("ceo/{ceoId}/restaurant/{restaurantId}/image")
    public ResponseEntity<BaseResponse> uploadRestaurantImg(@RequestParam MultipartFile file,
                                                            @PathVariable Long ceoId,
                                                            @PathVariable Long restaurantId) {
        BaseResponse baseResponse = "c";
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("ceo/{ceoId}/restaurant/{restaurantId}/logo")
    public ResponseEntity<BaseResponse> uploadLogoImg(@RequestParam MultipartFile file,
                                                      @PathVariable Long ceoId,
                                                      @PathVariable Long restaurantId){
        BaseResponse baseResponse = "b";
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("ceo/{ceoId}/restaurant/{restaurantId}/banner")
    public ResponseEntity<BaseResponse> uploadBannerImg(@RequestParam MultipartFile file,
                                                        @PathVariable Long ceoId,
                                                        @PathVariable Long restaurantId){
        BaseResponse baseResponse = "a";
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }
    */



}
