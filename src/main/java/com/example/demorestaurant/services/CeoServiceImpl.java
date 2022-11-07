package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.GetCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.exceptions.ExistingDataConflictException;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.entities.projections.CeoProjection;
import com.example.demorestaurant.repositories.ICeoRepository;
import com.example.demorestaurant.services.interfaces.ICeoService;
import com.example.demorestaurant.services.interfaces.IRestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CeoServiceImpl implements ICeoService {
    @Autowired
    private ICeoRepository repository;

    //create a ceo
    @Override
    public BaseResponse create(CreateCeoRequest request){
        //CeoProjection emailOrPhoneNumberExists = repository.findEmailOrPhoneNumberExists(request.getEmail(), request.getPhone_number());
           /* return (emailOrPhoneNumberExists == null) ?
                 BaseResponse.builder()
                .data(from(repository.save(from(request))))
                .message("ceo created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build() : validEmailAndPhoneNumber();*/
        return BaseResponse.builder()
                .data(from(repository.save(from(validEmailAndPhoneNumber2(request)))))
                .message("ceo created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    //valid email and phone number from ceo new
    /*@Override
    public BaseResponse validEmailAndPhoneNumber(){
        return BaseResponse.builder()
                .message("Data duplicated")
                .success(Boolean.FALSE)
                .httpStatus(HttpStatus.CONFLICT)
                .build();
    }*/

    public CreateCeoRequest validEmailAndPhoneNumber2(CreateCeoRequest request){
        boolean present = repository.findByEmailOrPhoneNumber(request.getEmail(), request.getPhoneNumber()).isPresent();
        if (present){
            throw new ExistingDataConflictException("Data Duplicated");
        }
        return request;
    }

    @Override
    public GetCeoResponse get(Long ceoId) {
        return from_get(FindAndEnsureExist(ceoId));
    }

    //get a ceo
    @Override
    public BaseResponse get(String email) {
        return BaseResponse.builder()
                .data(from_get(validationCeo(email)))
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
    public BaseResponse delete(Long ceoid) {
        repository.delete(FindAndEnsureExist(ceoid));
        return BaseResponse.builder()
                .message("Ceo deleted correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    //find a ceo by id and if not find ensure an exception
    @Override
    public Ceo FindAndEnsureExist(Long ceoId){
        return repository.findById(ceoId).orElseThrow(() -> new NotFoundException("ceo not found"));
    }

    private GetCeoResponse from_get(Ceo ceo){
        GetCeoResponse response = new GetCeoResponse();
        response.setId(ceo.getId());
        response.setName(ceo.getName());
        response.setFirstSurname(ceo.getFirstSurname());
        response.setSecondSurname(ceo.getSecondSurname());
        response.setEmail(ceo.getEmail());
        response.setPassword(ceo.getPassword());
        return response;
    }

    //from request to ceo
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

    //from ceo to response
    private CreateCeoResponse from(Ceo ceo){
        CreateCeoResponse response = new CreateCeoResponse();
        response.setId(ceo.getId());
        response.setName(ceo.getName());
        response.setFirstSurname(ceo.getFirstSurname());
        response.setSecondSurname(ceo.getSecondSurname());
        response.setEmail(ceo.getEmail());
        return response;
    }

    //validataion of Ceo
    private Ceo validationCeo(String email) {
        CeoProjection ceoProjection = repository.findCeoByEmail(email).orElseThrow(() -> new NotFoundException("ceo not found"));
        /*if (!Objects.equals(ceoProjection.getPassword(), request.getPassword())) {
            throw new NotFoundException("ceo not found");
        }*/
        //return ceo;
        return fromToCeo(ceoProjection);
    }

    //from ceoProjection to Ceo
    private Ceo fromToCeo(CeoProjection ceoProjection){
        Ceo response = new Ceo();
        response.setId(ceoProjection.getId());
        response.setName(ceoProjection.getName());
        response.setFirstSurname(ceoProjection.getFirstSurname());
        response.setSecondSurname(ceoProjection.getSecondSurname());
        response.setEmail(ceoProjection.getEmail());
        response.setPhoneNumber(ceoProjection.getPhoneNumber());
        response.setPassword(ceoProjection.getPassword());
        return response;
    }

    //form to Ceo to CeoResponse
    /*private CeoResponse fromToCeoResponse(Ceo ceo){
        CeoResponse response = new CeoResponse();
        response.setEmail(ceo.getEmail());
        response.setName(ceo.getName());
        response.setFirst_surname(ceo.getFirstSurname());
        response.setSecond_surname(ceo.getSecondSurname());
        response.setId(ceo.getId());
        response.setPhone_number(ceo.getPhoneNumber());
        return response;
    }*/

    //from Ceo to UpdateCeoResponse
    private UpdateCeoResponse fromToUpdateCeoResponse(Ceo ceo){
        UpdateCeoResponse response = new UpdateCeoResponse();
        response.setId(ceo.getId());
        response.setName(ceo.getName());
        response.setFirst_surname(ceo.getFirstSurname());
        response.setSecond_surname(ceo.getSecondSurname());
        response.setEmail(ceo.getEmail());
        response.setPhone_number(ceo.getPhoneNumber());
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

}
