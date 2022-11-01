package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.GetCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.entities.projections.CeoProjection;
import com.example.demorestaurant.entities.projections.RestaurantProjection2;
import com.example.demorestaurant.repositories.ICeoRepository;
import com.example.demorestaurant.services.interfaces.ICeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CeoServiceImpl implements ICeoService {
    @Autowired
    private ICeoRepository repository;

    //create a ceo
    @Override
    public BaseResponse create(CreateCeoRequest request) {
        return BaseResponse.builder()
                .data(from(repository.save(from(request))))
                .message("ceo created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public GetCeoResponse get(Long id) {
        return from_get(FindAndEnsureExist(id));
    }

    //get a ceo
    @Override
    public BaseResponse get(GetCeoRequest request) {
        return BaseResponse.builder()
                .data(fromToCeoResponse(validationCeo(request)))
                .message("access got from ceo")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    //update a ceo
    @Override
    public BaseResponse update(UpdateCeoRequest request, Long id) {
        Ceo ceo = validationUpdateDateCeo(request, id);
        return BaseResponse.builder()
                .data(fromToUpdateCeoResponse(repository.save(ceo)))
                .message("student update correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    //delete a ceo
    @Override
    public void delete(Long id) {
        repository.delete(FindAndEnsureExist(id));
    }

    //list all restaurants by ceo id
    @Override
    public BaseResponse listAllRestaurantsByCeoId(Long ceoId){
        return BaseResponse.builder()
                .data(repository.listAllRestaurantsByCeoId(ceoId)
                        .stream()
                        .map(this::from)
                        .toList().stream()
                        .map(this::from)
                        .collect(Collectors.toList()))
                .message("Restaurants list by ceos id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    //find a ceo by id and if not find ensure an exception
    @Override
    public Ceo FindAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("ceo not found"));
    }

    /*@Override
    public GetCeoResponse get(Long id) {
        return from_get(FindAndEnsureExist(id));
    }*/
    private GetCeoResponse from_get(Ceo ceo){
        GetCeoResponse response = new GetCeoResponse();
        response.setId(ceo.getId());
        response.setName(ceo.getName());
        response.setFirst_surname(ceo.getFirst_surname());
        response.setSecond_surname(ceo.getSecond_surname());
        response.setPhone_number(ceo.getPhone_number());
        response.setEmail(ceo.getEmail());
        return response;
    }

    /*@Override
    public List<RestaurantResponse> listRestaurantsFromCeoId(Long ceoId) {
        return repository.listRestaurantsFromCeoId(ceoId)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }*/

/*
    private RestaurantResponse from(RestaurantProjection2 restaurant){
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
*/

    //from request to ceo
    private Ceo from(CreateCeoRequest request){
        Ceo ceo = new Ceo();
        ceo.setName(request.getName());
        ceo.setFirst_surname(request.getFirst_surname());
        ceo.setSecond_surname(request.getSecond_surname());
        ceo.setPhone_number(request.getPhone_number());
        ceo.setEmail(request.getEmail());
        ceo.setPassword(request.getPassword());
        return ceo;
    }

    //from ceo to response
    private CreateCeoResponse from(Ceo ceo){
        CreateCeoResponse response = new CreateCeoResponse();
        response.setId(ceo.getId());
        response.setName(ceo.getName());
        response.setFirst_surname(ceo.getFirst_surname());
        response.setSecond_surname(ceo.getSecond_surname());
        response.setPhone_number(ceo.getPhone_number());
        response.setEmail(ceo.getEmail());
        return response;
    }

    //validataion of Ceo
    private Ceo validationCeo(GetCeoRequest request) {
        CeoProjection ceoProjection = repository.getCeoByEmail(request.getEmail());
        if (ceoProjection == null || !Objects.equals(ceoProjection.getPasswword(), request.getPassword())) {
            throw new NotFoundException("ceo not found");
        }
        return fromToCeo(ceoProjection);
    }

    //from ceoProjection to Ceo
    private Ceo fromToCeo(CeoProjection ceoProjection){
        Ceo response = new Ceo();
        response.setEmail(ceoProjection.getEmail());
        response.setName(ceoProjection.getName());
        response.setFirst_surname(ceoProjection.getFirst_surname());
        response.setSecond_surname(ceoProjection.getSecond_surname());
        response.setId(ceoProjection.getId());
        response.setPhone_number(ceoProjection.getPhone_number());
        response.setRestaurants(repository
                .listAllRestaurantsByCeoId(ceoProjection.getId())
                .stream()
                .map(this::from)
                .collect(Collectors.toList()));
        return response;
    }

    //form to Ceo to CeoResponse
    private CeoResponse fromToCeoResponse(Ceo ceo){
        CeoResponse response = new CeoResponse();
        response.setEmail(ceo.getEmail());
        response.setName(ceo.getName());
        response.setFirst_surname(ceo.getFirst_surname());
        response.setSecond_surname(ceo.getSecond_surname());
        response.setId(ceo.getId());
        response.setPhone_number(ceo.getPhone_number());
        return response;
    }

    //from Ceo to UpdateCeoResponse
    private UpdateCeoResponse fromToUpdateCeoResponse(Ceo ceo){
        UpdateCeoResponse response = new UpdateCeoResponse();
        response.setId(ceo.getId());
        response.setName(ceo.getName());
        response.setFirst_surname(ceo.getFirst_surname());
        response.setSecond_surname(ceo.getSecond_surname());
        response.setEmail(ceo.getEmail());
        return response;
    }

    //validation of dates from ceo to update
    private Ceo validationUpdateDateCeo (UpdateCeoRequest request, Long id){
        Ceo ceo = FindAndEnsureExist(id);
        if(request.getName().length() == 0 || request.getName() == null || Objects.equals(request.getName(), "")) {
            ceo.setName(ceo.getName());
        }else {
            ceo.setName(request.getName());
        }
        if(request.getPhone_number() == null || request.getPhone_number() == 0) {
            ceo.setPhone_number(ceo.getPhone_number());
        }else {
            ceo.setPhone_number(request.getPhone_number());
        }
        if(request.getEmail().length() == 0 || request.getEmail() == null || Objects.equals(request.getEmail(), "")) {
            ceo.setEmail(ceo.getEmail());
        }else {
            ceo.setEmail(request.getEmail());
        }
        if(request.getPassword().length() == 0 || request.getPassword() == null || Objects.equals(request.getPassword(), "")) {
            ceo.setPassword(ceo.getPassword());
        }else {
            ceo.setPassword(request.getPassword());
        }
        if(request.getFirst_surname().length() == 0 || request.getFirst_surname() == null || Objects.equals(request.getFirst_surname(), "")) {
            ceo.setFirst_surname(ceo.getFirst_surname());
        }else {
            ceo.setFirst_surname(request.getFirst_surname());
        }
        if(request.getSecond_surname().length() == 0 || request.getSecond_surname() == null || Objects.equals(request.getSecond_surname(), "")) {
            ceo.setSecond_surname(ceo.getSecond_surname());
        }else {
            ceo.setSecond_surname(request.getSecond_surname());
        }
        return ceo;
    }

    //from restaurantProjection to Restaurant
    private Restaurant from(RestaurantProjection2 restaurantProjection){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantProjection.getId());
        restaurant.setName(restaurantProjection.getName());
        restaurant.setPhone_number(restaurantProjection.getPhone_number());
        restaurant.setAddress(restaurantProjection.getAddress());
        restaurant.setSchedule(restaurantProjection.getSchedule());
        restaurant.setKitchen(restaurantProjection.getKitchen());
        restaurant.setCeo(FindAndEnsureExist(restaurantProjection.getCeo_id()));
        return restaurant;
    }

    //form Restaurant to RestaurantResponse
    private RestaurantResponse2 from(Restaurant restaurant){
        RestaurantResponse2 response = new RestaurantResponse2();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setPhoneNumber(restaurant.getPhone_number());
        response.setAddress(restaurant.getAddress());
        response.setSchedule(restaurant.getSchedule());
        response.setKitchent(restaurant.getKitchen());
        response.setCeoId(fromToCeoResponse(restaurant.getCeo()).getId());
        response.setCeoName(fromToCeoResponse(restaurant.getCeo()).getName());
        response.setCeoFirst_surname(fromToCeoResponse(restaurant.getCeo()).getFirst_surname());
        response.setCeoSecond_surname(fromToCeoResponse(restaurant.getCeo()).getSecond_surname());
        response.setCeoEmail(fromToCeoResponse(restaurant.getCeo()).getEmail());
        return response;
    }
}
