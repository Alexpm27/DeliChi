package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateUserRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateUserRequest;
import com.example.demorestaurant.controllers.dtos.response.CreateUserResponse;
import com.example.demorestaurant.controllers.dtos.response.GetUserResponse;
import com.example.demorestaurant.controllers.dtos.response.UpdateUserResponse;

import java.util.List;

public interface IUserService {

    GetUserResponse getUsers (Long id);

    List<GetUserResponse> userList ();

    CreateUserResponse createUser (CreateUserRequest request);

    UpdateUserResponse updateUser (Long id, UpdateUserRequest request);

    void deleteUser (Long id);
}
