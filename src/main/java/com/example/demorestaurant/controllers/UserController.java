package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.request.CreateUserRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateUserRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.CreateUserResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetUserResponse;
import com.example.demorestaurant.controllers.dtos.responses.UpdateUserResponse;
import com.example.demorestaurant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @GetMapping("{email}")
    public ResponseEntity<BaseResponse> get(@Valid @Email @PathVariable String email){
        BaseResponse baseResponse = service.get(email);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{userId}/reservations")
    public ResponseEntity<BaseResponse> ListReservationsByUserId(@Valid @PathVariable Long userId){
        BaseResponse baseResponse = service.ListReservationsByUserId(userId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateUserRequest request){
        BaseResponse baseResponse = service.create(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@Valid @RequestBody UpdateUserRequest request, @Valid @PathVariable Long id){
        BaseResponse baseResponse = service.update(request, id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> delete(@Valid @PathVariable Long id){
        BaseResponse baseResponse = service.delete(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
