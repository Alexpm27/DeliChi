package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateReservationRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateReservationRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Reservation;
import com.example.demorestaurant.entities.projections.ReservationProjection;
import com.example.demorestaurant.repositories.IReservationRepository;
import com.example.demorestaurant.services.interfaces.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class ReservationServiceImpl implements IReservationService {

    @Autowired
    private IReservationRepository repository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RestaurantServiceImpl resturantService;

    @Override
    public BaseResponse create(CreateReservationRequest request) {
        Reservation reservation = from(request);
        return BaseResponse.builder()
                .data(from(repository.save(reservation)))
                .message("reservation created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public GetReservationResponse get(Long id) {
        return from_get(FindAndEnsureExist(id));
    }

    @Override
    public BaseResponse update(UpdateReservationRequest request, Long id) {
        Reservation reservation = FindAndEnsureExist(id);
        reservation.setDate(request.getDate());
        reservation.setPeople(request.getPeople());
        return BaseResponse.builder()
                .data(from(repository.save(reservation)))
                .message("reservation updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public void delete(Long id) {
        repository.delete(FindAndEnsureExist(id));
    }

    @Override
    public BaseResponse ListReservationByRestaurantId(Long restaurantId) {
        List<ReservationProjection> reservation = repository.ListReservationByRestaurantId(restaurantId);
        List<GetReservationByRestaurantIdResponse> responses = reservation.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(responses)
                .message("reservation list by teacher id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private CreateReservationResponse from(Reservation reservation){
        CreateReservationResponse response = new CreateReservationResponse();
        response.setId(reservation.getId());
        response.setDate(reservation.getDate());
        response.setPeople(reservation.getPeople());
        response.setRestaurant_id(reservation.getId());
        response.setUser_id(reservation.getId());
        return response;
    }

    private Reservation from(CreateReservationRequest request){
        Reservation reservation = new Reservation();
        reservation.setDate(request.getDate());
        reservation.setPeople(request.getPeople());
        reservation.setUser(userService.FindAndEnsureExists(request.getUser_id()));
        reservation.setRestaurant(resturantService.FindRestaurantAndEnsureExist(request.getRestaurant_id()));
        return reservation;
    }

    //from Reservation to GetReservationResponse
    private GetReservationResponse from_get(Reservation reservation){
        GetReservationResponse response = new GetReservationResponse();
        response.setId(reservation.getId());
        response.setDate(reservation.getDate());
        response.setPeople(reservation.getPeople());
        response.setRestaurant_id(reservation.getId());
        response.setUser_id(reservation.getId());
        return response;
    }

    //from Reservation to UpdateReservationResponse
    private UpdateReservationResponse from_upd(Reservation reservation){
        UpdateReservationResponse response = new UpdateReservationResponse();
        response.setId(reservation.getId());
        response.setDate(reservation.getDate());
        response.setPeople(reservation.getPeople());
        response.setRestaurant_id(reservation.getId());
        response.setUser_id(reservation.getId());
        return response;
    }

    //from ReservationProyection to GetReservationByRestaurantIdResponse
    private GetReservationByRestaurantIdResponse from(ReservationProjection reservation){
        GetReservationByRestaurantIdResponse response = new GetReservationByRestaurantIdResponse();
        response.setName(reservation.getName());
        response.setLast_name(reservation.getLast_name());
        response.setDate(reservation.getDate());
        response.setPeople(reservation.getPeople());
        return response;
    }

    private Reservation FindAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("not fount"));
    }

}
