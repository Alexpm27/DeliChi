package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.request.CreateUserRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateUserRequest;
import com.example.demorestaurant.controllers.dtos.response.CreateUserResponse;
import com.example.demorestaurant.controllers.dtos.response.GetUserResponse;
import com.example.demorestaurant.controllers.dtos.response.UpdateUserResponse;
import com.example.demorestaurant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private IUserService service;

    @PostMapping
    public CreateUserResponse createUser (@RequestBody CreateUserRequest request){
        return service.createUser(request);
    }

    @GetMapping
    public List<GetUserResponse> ListUsers (){
        return service.userList();
    }

    @GetMapping("{id}")
    public GetUserResponse getUsers(@PathVariable Long id){
        return service.getUsers(id);
    }

    @PutMapping("{id}")
    public UpdateUserResponse updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request){
        return service.updateUser(id, request);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id){
        service.deleteUser(id);
    }
}
