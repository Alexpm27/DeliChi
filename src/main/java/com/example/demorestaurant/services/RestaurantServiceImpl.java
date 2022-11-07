package com.example.demorestaurant.services;


import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.Zone;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.entities.projections.*;
import com.example.demorestaurant.services.interfaces.ICeoService;
import com.example.demorestaurant.services.interfaces.IRestaurantService;
import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.repositories.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    @Autowired
    private IRestaurantRepository repository;

    @Autowired
    private CeoServiceImpl ceoService;

    @Autowired
    private ZoneServiceImpl zoneService;

    @Autowired
    private FileServiceImpl fileService;

    @Override
    public BaseResponse create(CreateRestaurantRequest request, Long ceoId){
        return BaseResponse.builder()
                .data(from(repository.save(from(request, ceoId))))
                .message("Restaurant created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(from_get(FindRestaurantAndEnsureExist(id)))
                .message("Restaurant got correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse getRestaurantByRestaurantId(Long restaurantId) {
        return BaseResponse.builder()
                .data(from_get(from2(repository.getRestaurantById(restaurantId)
                        .orElseThrow(()->new NotFoundException("Restaurant not found")))))
                .message("Restaurant by restaurant id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(UpdateRestaurantRequest request, Long id) {
        return BaseResponse.builder().data(
                from_upd(repository.save(from(request,id))))
                .message("Update Restaurant correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private UpdateRestaurantResponse from_upd(Restaurant restaurant){
        UpdateRestaurantResponse response = new UpdateRestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setBanner(restaurant.getBanner());
        response.setLogo(restaurant.getLogo());
        response.setAddress(restaurant.getAddress());
        response.setKitchen(restaurant.getKitchen());
        response.setPhoneNumber(restaurant.getPhoneNumber());
        response.setSchedule(restaurant.getSchedule());
        response.setZone(zoneService.FindAndEnsureExist(restaurant.getZone().getId()).getName());
        return response;
    }

    private Restaurant from(UpdateRestaurantRequest request, Long id){
        Restaurant restaurant = FindRestaurantAndEnsureExist(id);
        restaurant.setName(request.getName());
        restaurant.setBanner(request.getBanner());
        restaurant.setLogo(request.getLogo());
        restaurant.setAddress(request.getAddress());
        restaurant.setKitchen(request.getKitchen());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setSchedule(request.getSchedule());
        restaurant.setZone(zoneService.FindAndEnsureExist(request.getZoneId()));
        return restaurant;
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.delete(FindRestaurantAndEnsureExist(id));
        return BaseResponse.builder()
                .message("Restaurant deleted correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllRestaurantsByCeoId(Long ceoId) {
        List<RestaurantProjection> restaurants = repository.findAllByCeoId(ceoId)
                .orElseThrow(()->new NotFoundException("List of restaurants not found"));
        return BaseResponse.builder()
                .data(restaurants.stream()
                        .map(this::from)
                        .collect(Collectors.toList()))
                .message("Restaurant list by ceo id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllRestaurantsByName(String name) {
        List<RestaurantProjection> restaurants = repository.findAllByName(name)
                .orElseThrow(()->new NotFoundException("list of restaurants not fount"));

        return BaseResponse.builder()
                .data(restaurants.stream()
                        .map(this::from)
                        .collect(Collectors.toList())
                )
                .message("Restaurant list by her name")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public List<Restaurant> listAllRestaurantsByCeoId2(Long ceoId) {
        return repository.getRestaurantByCeo_Id(ceoId).orElseThrow(()->new NotFoundException("This ceo not have Restaurants"));
                /*.stream()
                .map(this::from2)
                .collect(Collectors.toList());*/
    }

    private Restaurant from2(RestaurantProjection restaurantProjection){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantProjection.getId());
        restaurant.setName(restaurantProjection.getName());
        restaurant.setLogo(restaurantProjection.getLogo());
        restaurant.setBanner(restaurantProjection.getBanner());
        restaurant.setKitchen(restaurantProjection.getKitchen());
        restaurant.setPhoneNumber(restaurantProjection.getPhoneNumber());
        restaurant.setSchedule(restaurantProjection.getSchedule());
        restaurant.setAddress(restaurantProjection.getAddress());
        Zone zone = zoneService.FindAndEnsureExist(restaurantProjection.getZoneId());
        restaurant.setZone(zone);
        Ceo ceo = ceoService.FindAndEnsureExist(restaurantProjection.getCeoId());
        restaurant.setCeo(ceo);
        return restaurant;
    }

    private GetRestaurantByCeoIdResponse from(RestaurantProjection restaurant) {
        GetRestaurantByCeoIdResponse response = new GetRestaurantByCeoIdResponse();
        response.setId(restaurant.getId());
        response.setLogo(restaurant.getLogo());
        response.setName(restaurant.getName());
        response.setZone(zoneService.FindAndEnsureExist(restaurant.getZoneId()).getName());
        return response;
    }

    private GetRestaurantByRestaurantIdResponse from(RestaurantByResturantIdProyection restaurant){
        GetRestaurantByRestaurantIdResponse response = new GetRestaurantByRestaurantIdResponse();
        List<String> images= repository.listAllImages(restaurant.getId())
                .stream()
                .map(ImageProection::getImages)
                .collect(Collectors.toList());
        List<CommentResponse> comments = repository.listAllComments(restaurant.getId()).stream()
                .map(this::from).collect(Collectors.toList());
        response.setId(restaurant.getId());
        response.setLogo(restaurant.getLogo());
        response.setBanner(restaurant.getBanner());
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setPhone_number(restaurant.getPhone_number());
        response.setKitchen(restaurant.getKitchen());
        response.setSchedule(restaurant.getSchedule());
        response.setZone(restaurant.getZone());
        response.setImages(images);
        response.setComments(comments);
        return response;
    }

    private CommentResponse from(CommentProjection comment){
        CommentResponse response = new CommentResponse();
        response.setContent(comment.getContent());
        response.setDate(comment.getDate());
        response.setScore(comment.getScore());
        return response;
    }

    private CreateRestaurantResponse from(Restaurant restaurant){
        CreateRestaurantResponse response = new CreateRestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setSchedule(restaurant.getSchedule());
        response.setAddress(restaurant.getAddress());
        response.setPhoneNumber(restaurant.getPhoneNumber());
        response.setKitchen(restaurant.getKitchen());
        response.setZone(restaurant.getZone().getName());
        return response;
    }
    private Restaurant from(CreateRestaurantRequest request, Long ceoId){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setAddress(request.getAddress());
        restaurant.setSchedule(request.getSchedule());
        restaurant.setZone(zoneService.FindAndEnsureExist(request.getZoneId()));
        restaurant.setKitchen(request.getKitchen());
        restaurant.setBanner(request.getBanner());
        restaurant.setLogo(request.getLogo());
        restaurant.setCeo(ceoService.FindAndEnsureExist(ceoId));
        return restaurant;
    }
    private GetRestaurantResponse from_get(Restaurant restaurant){
        GetRestaurantResponse response = new GetRestaurantResponse();
        response.setId(restaurant.getId());
        response.setName(restaurant.getName());
        response.setAddress(restaurant.getAddress());
        response.setKitchen(restaurant.getKitchen());
        response.setPhoneNumber(restaurant.getPhoneNumber());
        response.setSchedule(restaurant.getSchedule());
        response.setBanner(restaurant.getBanner());
        response.setLogo(restaurant.getLogo());
        response.setZoneId(restaurant.getZone().getId());
        response.setCeoId(restaurant.getCeo().getId());
        return response;
    }

    public Restaurant FindRestaurantAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Restaurant Not found"));
    }

}
