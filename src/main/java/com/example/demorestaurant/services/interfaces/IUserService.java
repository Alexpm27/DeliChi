package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateUserRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateUserRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.CreateUserResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetUserResponse;
import com.example.demorestaurant.controllers.dtos.responses.UpdateUserResponse;

import java.util.List;

public interface IUserService {

    GetUserResponse getUserById (Long id);

    BaseResponse userList ();

    BaseResponse createUser (CreateUserRequest request);

    BaseResponse updateUser (Long id, UpdateUserRequest request);

    void deleteUser (Long id);

    BaseResponse ListReservationsByUserId(Long userId);
}
