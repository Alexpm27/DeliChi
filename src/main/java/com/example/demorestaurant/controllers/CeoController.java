package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.services.interfaces.ICeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("ceo")
public class CeoController {
    @Autowired
    private ICeoService service;

    @GetMapping("{email}")
    public ResponseEntity<BaseResponse> get(@Valid @PathVariable String email){
        BaseResponse baseResponse = service.get(email);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}/restaurants")
    public ResponseEntity<BaseResponse> listAllRestaurantByCeoId(@Valid @PathVariable Long id){
        BaseResponse baseResponse = service.listAllRestaurantByCeoId(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping()
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateCeoRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@Valid @RequestBody UpdateCeoRequest request,
                                               @Valid @PathVariable Long id){
        BaseResponse baseResponse = service.update(request, id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> delete(@Valid @PathVariable Long id){
        BaseResponse baseResponse = service.delete(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
