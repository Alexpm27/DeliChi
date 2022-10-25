package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
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

    @PostMapping
    public CreateCeoResponse create(@RequestBody CreateCeoRequest request){
        return service.create(request);
    }

    @GetMapping("{id}")
    public GetCeoResponse get(@PathVariable Long id){
        return service.get(id);
    }

    @PutMapping("{id}")
    public UpdateCeoResponse update(@RequestBody UpdateCeoRequest request, @PathVariable Long id){
        return service.update(request, id);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable Long id){
        service.delete(id);
    }

    @GetMapping("restaurants/ceo/{ceoId}")
    public ResponseEntity<BaseResponse> listAllRestaurantsByCeoId(@PathVariable Long ceoId){
        BaseResponse baseResponse = service.listAllRestaurantsByCeoId(ceoId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
