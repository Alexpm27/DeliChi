package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.GetCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.services.interfaces.ICeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("ceo")
public class CeoController {
    @Autowired
    private ICeoService service;

    @PostMapping()
    public ResponseEntity<BaseResponse> create(@RequestBody CreateCeoRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping()
    public ResponseEntity<BaseResponse> get(@RequestBody GetCeoRequest request){
        BaseResponse baseResponse = service.get(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    /*@GetMapping("{id}")
    public GetCeoResponse get(@PathVariable Long id){
        return service.get(id);
    }*/

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@RequestBody UpdateCeoRequest request, @PathVariable Long id){
        BaseResponse baseResponse = service.update(request, id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("{ceoId}/restaurants")
    public ResponseEntity<BaseResponse> listAllRestaurantsByCeoId(@PathVariable Long ceoId){
        BaseResponse baseResponse = service.listAllRestaurantsByCeoId(ceoId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
