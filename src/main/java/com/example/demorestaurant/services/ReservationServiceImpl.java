package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateReservationRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateReservationRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Reservation;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.repositories.IReservationRepository;
import com.example.demorestaurant.services.interfaces.IReservationService;
import com.example.demorestaurant.services.interfaces.IRestaurantService;
import com.example.demorestaurant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private IReservationRepository repository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRestaurantService restaurantService;

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(fromReservationToGetReservationResponse(findAndEnsureExist(id)))
                .message("Reservation Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateReservationRequest request, Long userId, Long restaurantId) {
        return BaseResponse.builder()
                .data(from(repository.save(from(request, userId, restaurantId))))
                .message("Reservation Created Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(UpdateReservationRequest request, Long id) {
        return BaseResponse.builder()
                .data(fromReservationToUpdateReservationResponse(repository.save(validationUpdateDateReservation(request, id))))
                .message("reservation updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.delete(findAndEnsureExist(id));
        return BaseResponse.builder()
                .message("Reservation Deleted Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private Reservation findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    private GetReservationResponse fromReservationToGetReservationResponse(Reservation reservation){
        return GetReservationResponse.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .people(reservation.getPeople())
                .user(userService.fromUserToUserResponse(reservation.getUser()))
                .restaurant(restaurantService.fromRestaurantToRestaurantResponse(reservation.getRestaurant())).build();
    }

    private Reservation from(CreateReservationRequest request, Long userId, Long restaurantId){
        Reservation reservation = new Reservation();
        reservation.setDate(request.getDate());
        reservation.setPeople(request.getPeople());
        reservation.setUser(userService.findAndEnsureExists(userId));
        reservation.setRestaurant(restaurantService.findAndEnsureExist(restaurantId));
        return reservation;
    }

    private CreateReservationResponse from(Reservation reservation){
        return CreateReservationResponse.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .people(reservation.getPeople())
                .restaurant(restaurantService.fromRestaurantToRestaurantResponse(reservation.getRestaurant()))
                .user(userService.fromUserToUserResponse(reservation.getUser()))
                .build();
    }

    private Reservation validationUpdateDateReservation(UpdateReservationRequest request, Long id){
        Reservation reservation = findAndEnsureExist(id);
        if(request.getDate().length() == 0 || request.getDate() == null || Objects.equals(request.getDate(), "")) {
            reservation.setDate(reservation.getDate());
        }else {
            reservation.setDate(request.getDate());
        }
        if(request.getPeople() == null || request.getPeople() == 0) {
            reservation.setPeople(reservation.getPeople());
        }else {
            reservation.setPeople(request.getPeople());
        }
        return reservation;
    }

    private UpdateReservationResponse fromReservationToUpdateReservationResponse(Reservation reservation){
        return UpdateReservationResponse.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .people(reservation.getPeople())
                .user(userService.fromUserToUserResponse(reservation.getUser()))
                .restaurant(restaurantService.fromRestaurantToRestaurantResponse(reservation.getRestaurant())).build();
    }

    @Override
    public ReservationResponse fromReservationToReservationResponse(Reservation reservation){
        return ReservationResponse.builder()
                .people(reservation.getPeople())
                .date(reservation.getDate())
                .id(reservation.getId()).build();
    }

}
