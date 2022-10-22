package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.response.CreateRestaurantResponse;
import com.example.demorestaurant.controllers.dtos.response.GetRestaurantResponse;
import com.example.demorestaurant.controllers.dtos.response.UpdateRestaurantResponse;
import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.repositories.ICeoRepository;
import com.example.demorestaurant.repositories.IRestaurantRepository;
import com.example.demorestaurant.services.interfaces.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantServiceImpl implements IRestaurantService {
    @Autowired
    private IRestaurantRepository repository;

    @Autowired
    private ICeoRepository ceo_repository;

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
        restaurant.setCeo(FindCeoAndEnsureExist(request.getCeo_id()));
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

    private Ceo FindCeoAndEnsureExist(Long id){
        return ceo_repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

    private Restaurant FindRestaurantAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }
}
