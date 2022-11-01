package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateUserRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateUserRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.User;
import com.example.demorestaurant.entities.projections.UserReservationsProjection;
import com.example.demorestaurant.repositories.IUserRepository;
import com.example.demorestaurant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    // INTERFACE METHODS

    @Autowired
    private IUserRepository repository;

    @Override
    public GetUserResponse getUserById(Long id) {
        User user = FindAndEnsureExists(id);
        return from_get(user);
    }

    // List all users
    @Override
    public BaseResponse userList() {
        return BaseResponse.builder()
                .data(repository.findAll()
                        .stream()
                        .map(user -> from_get(user))
                        .collect(Collectors.toList()))
                .message("Users list by ceos id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    // Create a user
    @Override
    public BaseResponse createUser(CreateUserRequest request) {
        User user = from(request);
        return BaseResponse.builder()
                .data(from_create(repository.save(user)))
                .message("User created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    // Update a user
    @Override
    public BaseResponse updateUser(Long id, UpdateUserRequest request) {
        User user = FindAndEnsureExists(id);
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLast_name(request.getLast_name());
        user.setPhone_number(request.getPhone_number());

        return BaseResponse.builder()
                .data(from_upd(repository.save(user)))
                .message("User updated correctly")
                .httpStatus(HttpStatus.OK)
                .success(Boolean.TRUE)
                .build();
    }

    // Delete a user
    @Override
    public void deleteUser(Long id) {
        repository.delete(FindAndEnsureExists(id));
    }

    @Override
    public BaseResponse ListReservationsByUserId(Long userId) {
        List<UserReservationsProjection> resrvarions = repository.ListReservationsByUserId(userId);
        List<GetUserReservationsResponse> responses = resrvarions.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(responses)
                .message("reservation list by user id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    //from UserReservationsProjection to GetUserReservationsResponse
    private GetUserReservationsResponse from(UserReservationsProjection reservation){
        GetUserReservationsResponse response = new GetUserReservationsResponse();
        response.setName(reservation.getName());
        response.setDate(reservation.getDate());
        response.setPeople(reservation.getPeople());
        return response;
    }


    // METHODS THAT TRANSFORM A USER TO A RESPONSE

    // Transform a user to a CreateUserResponse
    private CreateUserResponse from_create (User user) {
        CreateUserResponse response = new CreateUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setLast_name(user.getLast_name());
        response.setPhone_number(user.getPhone_number());
        return response;
    }


    // Transform a user to a GetUserResponse
    private GetUserResponse from_get (User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setLast_name(user.getLast_name());
        response.setPhone_number(user.getPhone_number());
        return response;
    }

    // Transform a user to a UpdateUserResponse
    private UpdateUserResponse from_upd (User user){
        UpdateUserResponse response = new UpdateUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setLast_name(user.getLast_name());
        response.setPhone_number(user.getPhone_number());
        return response;
    }


    // methods that transform a request to a user

    // Transform a CreateUserRequest to a user and return a user
    private User from(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLast_name(request.getLast_name());
        user.setPhone_number(request.getPhone_number());
        user.setPassword(request.getPassword());
        return user;
    }

    // Other methods
    public User FindAndEnsureExists(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("ID NOT FOUND"));
    }

}
