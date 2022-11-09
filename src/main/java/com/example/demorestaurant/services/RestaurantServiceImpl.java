package com.example.demorestaurant.services;


import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.*;
import com.example.demorestaurant.entities.exceptions.InternalServerError;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.entities.projections.*;
import com.example.demorestaurant.services.interfaces.*;
import com.example.demorestaurant.controllers.dtos.request.CreateRestaurantRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateRestaurantRequest;
import com.example.demorestaurant.repositories.IRestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RestaurantServiceImpl implements IRestaurantService {

    @Autowired
    private IRestaurantRepository repository;

    @Autowired
    private ICeoService ceoService;

    @Autowired
    private IZoneService zoneService;

    @Autowired
    private IFileService fileService;

    @Autowired
    private ICommentService commentService;

    @Autowired
    private IReservationService reservationService;

    @Override
    public BaseResponse create(CreateRestaurantRequest request, Long ceoId, Long zoneId){
        try{
            return BaseResponse.builder()
                    .data(fromRestaurantToCreateRestaurantResponse(repository.save(from(request, ceoId, zoneId))))
                    .message("Restaurant Created Correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }catch (Error e){
            throw new InternalServerError();
        }
    }

    @Override
    public BaseResponse get(Long id) {
        try{
            return BaseResponse.builder()
                    .data(fromRestaurantToGetRestaurantResponse(FindAndEnsureExist(id)))
                    .message("Restaurant Got Correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }catch (Error e){
            throw new InternalServerError();
        }
    }

    @Override
    public BaseResponse list() {
        try{
            return BaseResponse.builder()
                    .data(repository.findAllRestaurants().orElseThrow(NotFoundException::new)
                            .stream()
                            .map(this::from)
                            .collect(Collectors.toList()))
                    .message("List All Restaurants")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }catch (Error e){
            throw new InternalServerError();
        }
    }

    @Override
    public BaseResponse update(UpdateRestaurantRequest request, Long id, Long zoneId) {
        try{
            return BaseResponse.builder()
                    .data(fromRestaurantToUpdateRestaurantResponse(repository.save(from(request, id, zoneId))))
                    .message("Update Restaurant Correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }catch (Error e){
            throw new InternalServerError();
        }
    }

    @Override
    public BaseResponse delete(Long id) {
        try{
            repository.delete(FindAndEnsureExist(id));
            return BaseResponse.builder()
                    .message("Restaurant Deleted Correctly")
                    .success(Boolean.TRUE)
                    .httpStatus(HttpStatus.OK).build();
        }catch (Error e){
            throw new InternalServerError();
        }
    }

    @Override
    public BaseResponse listAllRestaurantsByCeoId(Long ceoId) {
        List<RestaurantProjection> restaurants = repository.findAllByCeo_Id(ceoId)
                .orElseThrow(NotFoundException::new);
        return BaseResponse.builder()
                .data(restaurants.stream()
                        .map(this::fromRestaurantProjectionToRestaurant)
                        .map(this::from)
                        .collect(Collectors.toList()))
                .message("Restaurant list by ceo id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllRestaurantsByName(String name) {
        List<RestaurantProjection> restaurants = repository.findAllByName(name)
                .orElseThrow(NotFoundException::new);
        return BaseResponse.builder()
                .data(restaurants.stream()
                        .map(this::fromRestaurantProjectionToRestaurant)
                        .map(this::from)
                        .collect(Collectors.toList())
                )
                .message("Restaurant list by her name")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllRestaurantsByZoneId(Long zoneId) {
        List<RestaurantProjection> restaurants = repository.findAllByZone_Id(zoneId)
                .orElseThrow(NotFoundException::new);
        return BaseResponse.builder()
                .data(restaurants.stream()
                        .map(this::fromRestaurantProjectionToRestaurant)
                        .map(this::from)
                        .collect(Collectors.toList())
                )
                .message("Restaurant list by zone_id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public List<Restaurant> listAllRestaurantsByCeoId2(Long ceoId) {
        return repository.getRestaurantByCeo_Id(ceoId).orElseThrow(NotFoundException::new);
                /*.stream()
                .map(this::from2)
                .collect(Collectors.toList());*/
    }

    private Restaurant fromRestaurantProjectionToRestaurant(RestaurantProjection restaurantProjection){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantProjection.getId());
        restaurant.setName(restaurantProjection.getName());
        restaurant.setKitchen(restaurantProjection.getKitchen());
        restaurant.setPhoneNumber(restaurantProjection.getPhoneNumber());
        restaurant.setSchedule(restaurantProjection.getSchedule());
        restaurant.setAddress(restaurantProjection.getAddress());
        restaurant.setDescription(restaurantProjection.getDescription());
        restaurant.setMenu(restaurantProjection.getMenu());
        Zone zone = zoneService.FindAndEnsureExist(restaurantProjection.getZoneId());
        restaurant.setZone(zone);
        Ceo ceo = ceoService.FindAndEnsureExist(restaurantProjection.getCeoId());
        restaurant.setCeo(ceo);
        List<Image> images = repository.listAllImagesByRestaurantId(restaurant.getId())
                .orElseThrow(NotFoundException::new)
                .stream()
                .map(image -> fileService.fromFileProjectionToImage(image))
                .toList();
        restaurant.setImages(images);
        List<Comment> comments = repository.listAllCommentsByRestaurantId(restaurant.getId())
                .orElseThrow(NotFoundException::new)
                .stream()
                .map(comment -> commentService.from(comment)).collect(Collectors.toList());
        restaurant.setComments(comments);
        List<Reservation> reservations = repository.listAllReservationsByRestaurantId(restaurant.getId())
                .orElseThrow(NotFoundException::new)
                .stream()
                .map(reservation -> reservationService.fromReservationProjectionToReservation(reservation))
                .toList();
        restaurant.setReservations(reservations);
        return restaurant;
    }

    private ListRestaurantsResponse from(Restaurant restaurant) {
        return ListRestaurantsResponse.builder()
               /*
               el score con sql para sacar el puntaje que mas que se repite entre 1 a 5
                .score(images)
               sentencia sql para extraer el logo no lo hago por que es imagen y no se si jalo
                .logo(fileService.)*/
                .description(restaurant.getDescription())
                .zone(zoneService.FindAndEnsureExist(restaurant.getZone().getId()).getName())
                .id(restaurant.getId())
                .name(restaurant.getName()).build();
    }

    /*private CommentResponse from(CommentProjection comment){
        CommentResponse response = new CommentResponse();
        response.setContent(comment.getContent());
        response.setDate(comment.getDate());
        response.setScore(comment.getScore());
        return response;
    }*/

    private UpdateRestaurantResponse fromRestaurantToUpdateRestaurantResponse(Restaurant restaurant){
        return UpdateRestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .phoneNumber(restaurant.getPhoneNumber())
                .address(restaurant.getAddress())
                .schedule(restaurant.getSchedule())
                .menu(restaurant.getMenu())
                .zone(restaurant.getZone().getName())
                .kitchen(restaurant.getKitchen()).build();
    }

    private Restaurant from(UpdateRestaurantRequest request, Long id, Long zoneId){
        Restaurant restaurant = FindAndEnsureExist(id);
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setKitchen(request.getKitchen());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setSchedule(request.getSchedule());
        restaurant.setZone(zoneService.FindAndEnsureExist(zoneId));
        restaurant.setDescription(request.getDescription());
        restaurant.setMenu(restaurant.getMenu());
        return restaurant;
    }

    private CreateRestaurantResponse fromRestaurantToCreateRestaurantResponse(Restaurant restaurant){
        return CreateRestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .phoneNumber(restaurant.getPhoneNumber())
                .address(restaurant.getAddress())
                .schedule(restaurant.getSchedule())
                .menu(restaurant.getMenu())
                .zone(restaurant.getZone().getName())
                .kitchen(restaurant.getKitchen()).build();
    }
    private Restaurant from(CreateRestaurantRequest request, Long ceoId, Long zoneId){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setAddress(request.getAddress());
        restaurant.setSchedule(request.getSchedule());
        restaurant.setMenu(request.getMenu());
        restaurant.setZone(zoneService.FindAndEnsureExist(zoneId));
        restaurant.setKitchen(request.getKitchen());
        restaurant.setCeo(ceoService.FindAndEnsureExist(ceoId));
        return restaurant;
    }
    private GetRestaurantResponse fromRestaurantToGetRestaurantResponse(Restaurant restaurant){
        List<GetReservationResponse> reservationsResponse = repository.listAllReservationsByRestaurantId(restaurant.getId())
                .orElseThrow(NotFoundException::new)
                .stream()
                .map(reservation -> reservationService.fromReservationProjectionToReservation(reservation))
                .toList()
                .stream()
                .map(reser -> GetReservationResponse.builder()
                .restaurant_id(reser.getRestaurant().getId())
                .date(reser.getDate())
                .people(reser.getPeople())
                .user_id(reser.getUser().getId()).build())
                .collect(Collectors.toList());

        List<CommentResponse> commentsResponse = repository.listAllCommentsByRestaurantId(restaurant.getId()).orElseThrow(NotFoundException::new)
                .stream()
                .map(comment -> commentService.from(comment))
                .map(comm -> CommentResponse.builder()
                        .score(comm.getScore())
                        .content(comm.getContent())
                        .date(comm.getDate())
                        .build()).toList();

        List<GetImageResponse> imagesResponse = repository.listAllImagesByRestaurantId(restaurant.getId()).orElseThrow(NotFoundException::new)
                .stream()
                .map(image -> fileService.fromFileProjectionToImage(image))
                .map(img -> GetImageResponse.builder()
                        .fileUrl(img.getFileUrl())
                        .imgType(img.getImageType())
                        .name(img.getName())
                        .id(img.getId()).build())
                .collect(Collectors.toList());

        return GetRestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .phoneNumber(restaurant.getPhoneNumber())
                .address(restaurant.getAddress())
                .schedule(restaurant.getSchedule())
                .menu(restaurant.getMenu())
                .zoneId(restaurant.getZone().getId())
                .CeoId(restaurant.getCeo().getId())
                .kitchen(restaurant.getKitchen())
                .comments(commentsResponse)
                .images(imagesResponse)
                .reservations(reservationsResponse).build();
    }

    public Restaurant FindAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

}
