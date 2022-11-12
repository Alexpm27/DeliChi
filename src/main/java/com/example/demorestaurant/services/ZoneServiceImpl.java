package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetZoneResponse;
import com.example.demorestaurant.controllers.dtos.responses.RestaurantResponse;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.Zone;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.repositories.IZoneRepository;
import com.example.demorestaurant.services.interfaces.IZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ZoneServiceImpl implements IZoneService {

    @Autowired
    private IZoneRepository repository;

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(from(findAndEnsureExist(id)))
                .message("zones get correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list() {
        return BaseResponse.builder()
                .data(getZoneResponseList())
                .message("zones list correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllRestaurantByZoneId(Long id){
        return BaseResponse.builder()
                .data(getRestaurantResponseListByZoneId(id))
                .message("Restaurant List By Zone Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public Zone findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    @Override
    public GetZoneResponse from(Zone zone){
        return GetZoneResponse.builder()
                .id(zone.getId())
                .name(zone.getName())
                .build();
    }

    private List<GetZoneResponse> getZoneResponseList(){
        return getZoneList().stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private List<Zone> getZoneList(){
        return repository.findAll();
    }

    private List<RestaurantResponse> getRestaurantResponseListByZoneId(Long id){
        return getRestaurantListByZoneId(id)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private List<Restaurant> getRestaurantListByZoneId(Long id){
        return findAndEnsureExist(id)
                .getRestaurants();
    }

    private RestaurantResponse from(Restaurant restaurant){
        return RestaurantResponse.builder()
                .name(restaurant.getName())
                .id(restaurant.getId())
                .build();
    }

}