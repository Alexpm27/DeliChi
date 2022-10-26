package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.request.CreateUserRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateUserRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.CreateUserResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetUserResponse;
import com.example.demorestaurant.controllers.dtos.responses.UpdateUserResponse;
import com.example.demorestaurant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping
    public ResponseEntity<BaseResponse> createUser (@RequestBody CreateUserRequest request){
        BaseResponse baseResponse = service.createUser(request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping
    public ResponseEntity<BaseResponse> ListUsers (){
        BaseResponse baseResponse = service.userList();
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public GetUserResponse getUsers(@PathVariable Long id){
        return service.getUserById(id);
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request){
        BaseResponse baseResponse = service.updateUser(id, request);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}
