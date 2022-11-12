package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.*;
import com.example.demorestaurant.entities.exceptions.InternalServerError;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
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
    private IUserService userService;

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(fromRestaurantToGetRestaurantResponse(findAndEnsureExist(id)))
                .message("Restaurant Got Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse list(){
        return BaseResponse.builder()
                .data(restaurantResponseList())
                .message("List All Restaurants")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllRestaurantsByName(String name) {
        return BaseResponse.builder()
                .data(getResRestaurantResponse(name))
                .message("Restaurant list by her name")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateRestaurantRequest request, Long ceoId, Long zoneId){
        return BaseResponse.builder()
                .data(fromRestaurantToCreateRestaurantResponse(repository.save(from(request, ceoId, zoneId))))
                .message("Restaurant Created Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(UpdateRestaurantRequest request, Long id, Long zoneId) {
        return BaseResponse.builder()
                .data(fromRestaurantToUpdateRestaurantResponse(repository.save(from(request, id, zoneId))))
                .message("Update Restaurant Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.delete(findAndEnsureExist(id));
        return BaseResponse.builder()
                .message("Restaurant Deleted Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Restaurant findAndEnsureExist(Long id) {
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    private GetRestaurantResponse fromRestaurantToGetRestaurantResponse(Restaurant restaurant){
        return GetRestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .description(restaurant.getDescription())
                .phoneNumber(restaurant.getPhoneNumber())
                .address(restaurant.getAddress())
                .schedule(restaurant.getSchedule())
                .menu(restaurant.getMenu())
                .zone(zoneService.from(restaurant.getZone()))
                .ceo(ceoService.fromCeoToCeoResponse(restaurant.getCeo()))
                .kitchen(restaurant.getKitchen())
                .comments(getCommentResponseList(restaurant.getId()))
                .reservations(getReservationResponseList(restaurant.getId()))
                .images(getImageResponsesList(restaurant.getId()))
                .build();
    }

    @Override
    public List<GetImageResponse> getImageResponsesList(Long id){
        return getImageList(id)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    public GetImageResponse from(Image image){
        return GetImageResponse.builder()
                .id(image.getId())
                .fileUrl(image.getFileUrl())
                .imageType(image.getImageType())
                .build();
    }

    private List<Image> getImageList(Long id){
        return findAndEnsureExist(id)
                .getImages();
    }

    private List<GetCommentResponse> getCommentResponseList(Long id){
        return getCommentList(id)
                .stream()
                .map(this::fromCommentToGetCommentResponse)
                .collect(Collectors.toList());
    }

    private GetCommentResponse fromCommentToGetCommentResponse(Comment comment){
        return GetCommentResponse.builder()
                .id(comment.getId())
                .date(comment.getDate())
                .score(comment.getScore())
                .content(comment.getContent())
                .user(userService.fromUserToUserResponse(comment.getUser()))
                .restaurant(fromRestaurantToRestaurantResponse(comment.getRestaurant())).build();
    }

    private List<Comment> getCommentList(Long id){
        return findAndEnsureExist(id)
                .getComments();
    }

    private List<GetReservationResponse> getReservationResponseList(Long id){
        return getReservationList(id)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private GetReservationResponse from(Reservation reservation){
        return GetReservationResponse.builder()
                .id(reservation.getId())
                .date(reservation.getDate())
                .people(reservation.getPeople())
                .user(userService.fromUserToUserResponse(reservation.getUser()))
                .restaurant(fromRestaurantToRestaurantResponse(reservation.getRestaurant())).build();
    }

    private List<Reservation> getReservationList(Long id){
        return findAndEnsureExist(id)
                .getReservations();
    }

    private List<Restaurant> restaurantList(){
        return repository.findAllRestaurants().orElseThrow(NotFoundException::new);
    }

    private List<RestaurantResponse> restaurantResponseList(){
        return restaurantList()
                .stream()
                .map(this::fromRestaurantToRestaurantResponse)
                .collect(Collectors.toList());
    }

    @Override
    public RestaurantResponse fromRestaurantToRestaurantResponse(Restaurant restaurant){
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .image(getImageResponsesList(restaurant.getId())).build();
    }

    private List<Restaurant> getRestaurantListByName(String name){
        return repository.findAllByName(name).orElseThrow(NotFoundException::new);
    }

    private List<RestaurantResponse> getResRestaurantResponse(String name){
        return getRestaurantListByName(name)
                .stream()
                .map(this::fromRestaurantToRestaurantResponse)
                .collect(Collectors.toList());
    }

    private Restaurant from(CreateRestaurantRequest request, Long ceoId, Long zoneId){
        Restaurant restaurant = new Restaurant();
        restaurant.setName(request.getName());
        restaurant.setDescription(request.getDescription());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setAddress(request.getAddress());
        restaurant.setSchedule(request.getSchedule());
        restaurant.setMenu(request.getMenu());
        restaurant.setZone(zoneService.findAndEnsureExist(zoneId));
        restaurant.setKitchen(request.getKitchen());
        restaurant.setCeo(ceoService.findAndEnsureExist(ceoId));
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



    private Restaurant from(UpdateRestaurantRequest request, Long id, Long zoneId){
        Restaurant restaurant = findAndEnsureExist(id);
        restaurant.setName(request.getName());
        restaurant.setAddress(request.getAddress());
        restaurant.setKitchen(request.getKitchen());
        restaurant.setPhoneNumber(request.getPhoneNumber());
        restaurant.setSchedule(request.getSchedule());
        restaurant.setZone(zoneService.findAndEnsureExist(zoneId));
        restaurant.setDescription(request.getDescription());
        restaurant.setMenu(restaurant.getMenu());
        return restaurant;
    }

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

    @Override
    public BaseResponse listAllImagesByRestaurantId(Long restaurantId) {
        return BaseResponse.builder()
                .data(getImageResponsesList(restaurantId))
                .message("Images by restaurant ID")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    /*private Restaurant fromRestaurantProjectionToRestaurant(RestaurantProjection restaurantProjection){
        Restaurant restaurant = new Restaurant();
        restaurant.setId(restaurantProjection.getId());
        restaurant.setName(restaurantProjection.getName());
        restaurant.setKitchen(restaurantProjection.getKitchen());
        restaurant.setPhoneNumber(restaurantProjection.getPhoneNumber());
        restaurant.setSchedule(restaurantProjection.getSchedule());
        restaurant.setAddress(restaurantProjection.getAddress());
        restaurant.setDescription(restaurantProjection.getDescription());
        restaurant.setMenu(restaurantProjection.getMenu());
        Zone zone = zoneService.findAndEnsureExist(restaurantProjection.getZoneId());
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
    }*/
/*
    private ListRestaurantsResponse from(Restaurant restaurant) {
        return ListRestaurantsResponse.builder()

               el score con sql para sacar el puntaje que mas que se repite entre 1 a 5
                .score(images)
               sentencia sql para extraer el logo no lo hago por que es imagen y no se si jalo
                .logo(fileService.)
                .description(restaurant.getDescription())
                .zone(zoneService.findAndEnsureExist(restaurant.getZone().getId()).getName())
                .id(restaurant.getId())
                .name(restaurant.getName()).build();
    }*/

/*
    private GetImageResponse fromImageToGetImageResponse(Image image){
        return GetImageResponse.builder()
                .id(image.getId())
                .fileUrl(image.getFileUrl())
                .imageType(image.getImageType())
                .build();
    }

    public List<String> url(Long restaurantId, String type){
        List<GetImageResponse> imageList = getImageList(restaurantId);
        List<String> url = imageList.stream().filter(img -> img.getImageType().equals(type))
                .map(img -> img.getFileUrl()).collect(Collectors.toList());
        return url;
    }
*/

}
