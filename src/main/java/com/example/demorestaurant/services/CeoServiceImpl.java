package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.exceptions.ExistingDataConflictException;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.repositories.ICeoRepository;
import com.example.demorestaurant.services.interfaces.ICeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CeoServiceImpl implements ICeoService {
    @Autowired
    private ICeoRepository repository;

    @Override
    public BaseResponse get(String email) {
        return BaseResponse.builder()
                .data(fromCeoToGetCeoResponse(findCeoByEmail(email)))
                .message("Ceo Obtained")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse listAllRestaurantByCeoId(Long id) {
        return BaseResponse.builder()
                .data(getRestaurantResponseList(id))
                .message("Restaurant List By Ceo Id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse create(CreateCeoRequest request){
        return BaseResponse.builder()
                .data(from(repository.save(from(validateEmailAndPhoneNumberExists(request)))))
                .message("Ceo Created Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(UpdateCeoRequest request, Long id) {
        return BaseResponse.builder()
                .data(fromCeoToUpdateCeoResponse(repository.save(validationUpdateDateCeo(request, id))))
                .message("Ceo Update Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.delete(findAndEnsureExist(id));
        return BaseResponse.builder()
                .message("Ceo Deleted Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public Ceo findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    private Ceo findCeoByEmail(String email) {
        return repository.findCeoByEmail(email).orElseThrow(NotFoundException::new);
    }

    private GetCeoResponse fromCeoToGetCeoResponse(Ceo ceo) {
        return GetCeoResponse.builder()
                .id(ceo.getId())
                .name(ceo.getName())
                .firstSurname(ceo.getFirstSurname())
                .secondSurname(ceo.getSecondSurname())
                .email(ceo.getEmail())
                .password(ceo.getPassword()).build();
    }

    private CreateCeoRequest validateEmailAndPhoneNumberExists(CreateCeoRequest request){
        if (repository.findByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber()).isPresent()){
            throw new ExistingDataConflictException();
        }
        return request;
    }

    private List<RestaurantResponse> getRestaurantResponseList(Long id){
        return getRestaurantList(id)
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private List<Restaurant> getRestaurantList(Long id){
        return findAndEnsureExist(id)
                .getRestaurants();
    }

    private RestaurantResponse from(Restaurant restaurant){
        return RestaurantResponse.builder()
                .id(restaurant.getId())
                .name(restaurant.getName()).build();
    }

    private Ceo from(CreateCeoRequest request){
        Ceo ceo = new Ceo();
        ceo.setName(request.getName());
        ceo.setFirstSurname(request.getFirstSurname());
        ceo.setSecondSurname(request.getSecondSurname());
        ceo.setPhoneNumber(request.getPhoneNumber());
        ceo.setEmail(request.getEmail());
        ceo.setPassword(request.getPassword());
        return ceo;
    }

    private CreateCeoResponse from(Ceo ceo){
        return CreateCeoResponse.builder()
                .id(ceo.getId())
                .name(ceo.getName())
                .firstSurname(ceo.getFirstSurname())
                .secondSurname(ceo.getSecondSurname())
                .email(ceo.getEmail()).build();
    }

    private Ceo validationUpdateDateCeo(UpdateCeoRequest request, Long id){
        Ceo ceo = findAndEnsureExist(id);
        if(request.getName().length() == 0 || request.getName() == null || Objects.equals(request.getName(), "")) {
            ceo.setName(ceo.getName());
        }else {
            ceo.setName(request.getName());
        }
        if(request.getPhoneNumber() == null || request.getPhoneNumber() == 0) {
            ceo.setPhoneNumber(ceo.getPhoneNumber());
        }else {
            ceo.setPhoneNumber(request.getPhoneNumber());
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
        if(request.getFirstSurname().length() == 0 || request.getFirstSurname() == null || Objects.equals(request.getFirstSurname(), "")) {
            ceo.setFirstSurname(ceo.getFirstSurname());
        }else {
            ceo.setFirstSurname(request.getFirstSurname());
        }
        if(request.getSecondSurname().length() == 0 || request.getSecondSurname() == null || Objects.equals(request.getSecondSurname(), "")) {
            ceo.setSecondSurname(ceo.getSecondSurname());
        }else {
            ceo.setSecondSurname(request.getSecondSurname());
        }
        return ceo;
    }

    private UpdateCeoResponse fromCeoToUpdateCeoResponse(Ceo ceo){
        return UpdateCeoResponse.builder()
                .id(ceo.getId())
                .name(ceo.getName())
                .firstSurname(ceo.getFirstSurname())
                .secondSurname(ceo.getSecondSurname())
                .email(ceo.getEmail())
                .phoneNumber(ceo.getPhoneNumber()).build();
    }

    @Override
    public CeoResponse fromCeoToCeoResponse(Ceo ceo){
        return CeoResponse.builder()
                .id(ceo.getId())
                .name(ceo.getName())
                .firstSurname(ceo.getFirstSurname())
                .secondSurname(ceo.getSecondSurname())
                .phoneNumber(ceo.getPhoneNumber())
                .email(ceo.getEmail())
                .build();
    }
    /*
    public List<GetImageResponse> getImageResponseList(Long ceoId){
        return findAndEnsureExist(ceoId)
                .getImages()
                .stream()
                .map(this::from)
                .collect(Collectors.toList());
    }

    private GetImageResponse from(Image image){
        return GetImageResponse.builder()
                .id(image.getId())
                .fileUrl(image.getFileUrl())
                .imageType(image.getImageType())
                .build();
    }
    */
}
