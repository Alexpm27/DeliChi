package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateUserRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateUserRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Reservation;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.User;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.repositories.IUserRepository;
import com.example.demorestaurant.services.interfaces.IImageService;
import com.example.demorestaurant.services.interfaces.IRestaurantService;
import com.example.demorestaurant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository repository;

    @Override
    public BaseResponse get(String email) {
        return BaseResponse.builder()
                .data(fromUserToGetUserResponse(findUserByEmailAndEnsureExists(email)))
                .message("User Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse ListReservationsByUserId(Long userId) {
        return BaseResponse.builder()
                .data(getReservationList(userId))
                .message("Reservations List By User")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateUserRequest request) {
        return BaseResponse.builder()
                .data(from(repository.save(from(request))))
                .message("User Created Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(UpdateUserRequest request, Long id) {
        return BaseResponse.builder()
                .data(fromUserToUpdateUserResponse(repository.save(from(request, id))))
                .message("User Updated Correctly")
                .httpStatus(HttpStatus.OK)
                .success(Boolean.TRUE)
                .build();
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.delete(findAndEnsureExists(id));
        return BaseResponse.builder()
                .message("User Deleted Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public User findAndEnsureExists(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    private User findUserByEmailAndEnsureExists(String email) {
        return repository.findByEmail(email).orElseThrow(NotFoundException::new);
    }

    private GetUserResponse fromUserToGetUserResponse(User user) {
        return GetUserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .password(user.getPassword()).build();
    }

    private List<GetReservationResponse> getReservationList(Long userId){
        return findAndEnsureExists(userId)
                .getReservations()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private GetReservationResponse from(Reservation reservation){
        return GetReservationResponse.builder()
                .date(reservation.getDate())
                .people(reservation.getPeople())
                .id(reservation.getId())
                .user(fromUserToUserResponse(reservation.getUser()))
                .restaurant(fromRestaurantToRestaurantResponse(reservation.getRestaurant()))
                .build();
    }

    @Override
    public UserResponse fromUserToUserResponse(User user){
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .lastName(user.getLastName())
                .build();
    }

    private RestaurantResponse fromRestaurantToRestaurantResponse(Restaurant restaurant){
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .build();
    }

    private User from(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        return user;
    }

    private CreateUserResponse from(User user) {
        return CreateUserResponse.builder()
                .phoneNumber(user.getPhoneNumber())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .name(user.getName())
                .id(user.getId()).build();
    }

    private User from(UpdateUserRequest request, Long id){
        User user = validationUpdateDateUser(request, id);
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLastName(request.getLastName());
        user.setPhoneNumber(request.getPhoneNumber());
        user.setPassword(request.getPassword());
        return user;
    }

    private User validationUpdateDateUser(UpdateUserRequest request, Long id){
        User user = findAndEnsureExists(id);
        if(request.getName().length() == 0 || request.getName() == null || Objects.equals(request.getName(), "")) {
            user.setName(user.getName());
        }else {
            user.setName(request.getName());
        }
        if(request.getPhoneNumber() == null || request.getPhoneNumber() == 0) {
            user.setPhoneNumber(user.getPhoneNumber());
        }else {
            user.setPhoneNumber(request.getPhoneNumber());
        }
        if(request.getEmail().length() == 0 || request.getEmail() == null || Objects.equals(request.getEmail(), "")) {
            user.setEmail(user.getEmail());
        }else {
            user.setEmail(request.getEmail());
        }
        if(request.getPassword().length() == 0 || request.getPassword() == null || Objects.equals(request.getPassword(), "")) {
            user.setPassword(user.getPassword());
        }else {
            user.setPassword(request.getPassword());
        }
        if(request.getLastName().length() == 0 || request.getLastName() == null || Objects.equals(request.getLastName(), "")) {
            user.setLastName(user.getLastName());
        }else {
            user.setLastName(request.getLastName());
        }
        return user;
    }

    private UpdateUserResponse fromUserToUpdateUserResponse(User user){
        return UpdateUserResponse.builder()
                .lastName(user.getLastName())
                .email(user.getEmail())
                .id(user.getId())
                .phoneNumber(user.getPhoneNumber())
                .name(user.getName()).build();
    }

}
