package com.example.demorestaurant.services;


import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.Image;
import com.example.demorestaurant.entities.Zone;
import com.example.demorestaurant.entities.projections.CommentProjection;
import com.example.demorestaurant.entities.projections.RestaurantByResturantIdProyection;
import com.example.demorestaurant.entities.projections.ResturantByCeoIdProjection;
import com.example.demorestaurant.services.interfaces.IRestaurantService;
import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.repositories.IRestaurantRepository;
import com.example.demorestaurant.entities.projections.ImageProection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    @Autowired
    private IRestaurantRepository repository;

    @Autowired
    private CeoServiceImpl ceoService;

    @Autowired
    private ZoneServiceImpl zoneService;

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
        restaurant.setBanner(request.getBanner());
        restaurant.setLogo(request.getLogo());
        restaurant.setAddress(request.getAddress());
        restaurant.setKitchen(request.getKitchen());
        restaurant.setPhone_number(request.getPhone_number());
        restaurant.setSchedule(request.getSchedule());
        restaurant.setZone(zoneService.FindAndEnsureExist(request.getZone_id()));
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
    public BaseResponse listAllRestaurantsByCeoId(Long ceoId) {
        List<ResturantByCeoIdProjection> resturants = repository.listAllRestaurantsByCeoId(ceoId);
        List<GetRestaurantByCeoIdResponse> responses = resturants.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(responses)
                .message("Restaurant by ceo id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private GetRestaurantByCeoIdResponse from(ResturantByCeoIdProjection resturant) {
        GetRestaurantByCeoIdResponse response = new GetRestaurantByCeoIdResponse();
        response.setId(resturant.getId());
        response.setLogo(resturant.getLogo());
        response.setName(resturant.getName());
        response.setZone(resturant.getZone());
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
        return response;
    }
    private Restaurant from(CreateRestaurantRequest request){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setBanner(request.getBanner());
        restaurant.setLogo(request.getLogo());
        restaurant.setAddress(request.getAddress());
        restaurant.setKitchen(request.getKitchen());
        restaurant.setPhone_number(request.getPhone_number());
        restaurant.setSchedule(request.getSchedule());
        restaurant.setCeo(ceoService.FindAndEnsureExist(request.getCeo_id()));
        restaurant.setZone(zoneService.FindAndEnsureExist(request.getZone_id()));
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
