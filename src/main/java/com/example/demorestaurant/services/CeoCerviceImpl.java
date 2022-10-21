package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateCeoRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCeoRequest;
import com.example.demorestaurant.controllers.dtos.response.CreateCeoResponse;
import com.example.demorestaurant.controllers.dtos.response.GetCeoResponse;
import com.example.demorestaurant.controllers.dtos.response.UpdateCeoResponse;
import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.repositories.ICeoRepository;
import com.example.demorestaurant.services.interfaces.ICeoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CeoCerviceImpl implements ICeoService {
    @Autowired
    private ICeoRepository repository;

    @Override
    public CreateCeoResponse create(CreateCeoRequest request) {
        return from(repository.save(from(request)));
    }

    @Override
    public GetCeoResponse get(Long id) {
        return from_get(FindAndEnsureExist(id));
    }

    @Override
    public UpdateCeoResponse update(UpdateCeoRequest request, Long id) {
        Ceo ceo = FindAndEnsureExist(id);
        ceo.setPhone_number(request.getPhone_number());
        ceo.setEmail(request.getEmail());
        ceo.setPassword(request.getPassword());
        return from_upd(repository.save(ceo));
    }

    @Override
    public void delete(Long id) {
        repository.delete(FindAndEnsureExist(id));
    }

    CreateCeoResponse from(Ceo ceo){
        CreateCeoResponse response = new CreateCeoResponse();
        response.setId(ceo.getId());
        response.setName(ceo.getName());
        response.setLast_name(ceo.getLast_name());
        response.setPhone_number(ceo.getPhone_number());
        response.setEmail(ceo.getEmail());
        response.setPassword(ceo.getPassword());
        return response;
    }
    Ceo from(CreateCeoRequest request){
        Ceo ceo = new Ceo();
        ceo.setName(request.getName());
        ceo.setLast_name(request.getLast_name());
        ceo.setPhone_number(request.getPhone_number());
        ceo.setEmail(request.getEmail());
        ceo.setPassword(request.getPassword());
        return ceo;
    }

    GetCeoResponse from_get(Ceo ceo){
        GetCeoResponse response = new GetCeoResponse();
        response.setId(ceo.getId());
        response.setName(ceo.getName());
        response.setLast_name(ceo.getLast_name());
        response.setPhone_number(ceo.getPhone_number());
        response.setEmail(ceo.getEmail());
        response.setPassword(ceo.getPassword());
        return response;
    }

    UpdateCeoResponse from_upd(Ceo ceo){
        UpdateCeoResponse response = new UpdateCeoResponse();
        response.setId(ceo.getId());
        response.setName(ceo.getName());
        response.setLast_name(ceo.getLast_name());
        response.setPhone_number(ceo.getPhone_number());
        response.setEmail(ceo.getEmail());
        response.setPassword(ceo.getPassword());
        return response;
    }

    private Ceo FindAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("not found"));
    }

}
