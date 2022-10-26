package com.example.demorestaurant.services;


import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.projections.RestaurantProjection;
import com.example.demorestaurant.services.interfaces.IRestaurantService;
import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.repositories.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
    @Autowired
    private IRestaurantRepository repository;

    @Autowired
    private CeoServiceImpl service;

    public CreateRestaurantResponse create(CreateRestaurantRequest request){
        return from(repository.save(from(request)));
    }

    @Override
    public GetRestaurantResponse get(Long id) {
        return from_get(FindRestaurantAndEnsureExist(id));
    }

    @Override
    public UpdateRestaurantResponse update(UpdateRestaurantRequest request, Long id) {
        Restaurant restaurant = FindRestaurantAndEnsureExist(id);
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setKitchen(request.getKitchen());
        restaurant.setPhone_number(request.getPhone_number());
        restaurant.setSchedule(request.getSchedule());
        return from_upd(repository.save(restaurant));
    }

    @Override
    public void delete(Long id) {
        repository.delete(FindRestaurantAndEnsureExist(id));
    }


    @Override
    public BaseResponse getRestaurantByRestaurantId(Long restaurantId) {
        return BaseResponse.builder()
                .data(from(repository.getRestaurantByRestaurantId(restaurantId)))
                .message("Restaurant by restaurant id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllRestaurants() {
        return BaseResponse.builder()
                .data(repository.listAllRestaurants()
                        .stream()
                        .map(this::from)
                        .collect(Collectors.toList()))
                .message("Restaurants list")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private RestaurantResponse from(RestaurantProjection restaurant){
        RestaurantResponse response = new RestaurantResponse();
        response.setCeoId(restaurant.getCeoId());
        response.setCeoName(restaurant.getCeoName());
        response.setRestaurantId(restaurant.getRestaurantId());
        response.setRestaurantName(restaurant.getRestaurantName());
        response.setRestaurantAddress(restaurant.getRestaurantAddress());
        response.setZoneId(restaurant.getZoneId());
        response.setZoneName(restaurant.getZoneName());
        response.setRestaurantPhoneNumber(restaurant.getRestaurantPhoneNumber());
        response.setRestaurantKitchent(restaurant.getRestaurantKitchent());
        response.setRestaurantSchedule(restaurant.getRestaurantSchedule());
        return response;
    }

    private CreateRestaurantResponse from(Restaurant restaurant){
        CreateRestaurantResponse response = new CreateRestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        return response;
    }
    private Restaurant from(CreateRestaurantRequest request){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setKitchen(request.getKitchen());
        restaurant.setPhone_number(request.getPhone_number());
        restaurant.setSchedule(request.getSchedule());
        restaurant.setCeo(service.FindAndEnsureExist(request.getCeo_id()));
        return restaurant;
    }
    private GetRestaurantResponse from_get(Restaurant restaurant){
        GetRestaurantResponse response = new GetRestaurantResponse();
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setKitchen(restaurant.getKitchen());
        response.setPhone_number(restaurant.getPhone_number());
        response.setSchedule(restaurant.getSchedule());
        return response;
    }

    private UpdateRestaurantResponse from_upd(Restaurant restaurant){
        UpdateRestaurantResponse response = new UpdateRestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        return response;
    }

    public Restaurant FindRestaurantAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
}
