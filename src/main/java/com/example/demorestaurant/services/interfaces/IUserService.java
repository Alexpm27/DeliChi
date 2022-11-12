package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateUserRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateUserRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.RestaurantResponse;
import com.example.demorestaurant.controllers.dtos.responses.UserResponse;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.User;

public interface IUserService {

    BaseResponse get(String email);

    BaseResponse create(CreateUserRequest request);

    BaseResponse update(UpdateUserRequest request, Long id);

    BaseResponse delete (Long id);

    BaseResponse ListReservationsByUserId(Long userId);

    UserResponse fromUserToUserResponse(User user);

    User findAndEnsureExists(Long id);

}
