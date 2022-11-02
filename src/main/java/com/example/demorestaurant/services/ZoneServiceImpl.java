package com.example.demorestaurant.services;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetZoneResponse;
import com.example.demorestaurant.entities.Zone;
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
        Zone zone = FindAndEnsureExist(id);
        return BaseResponse.builder()
                .data(from(zone))
                .message("zones get correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse ListAllZones() {
        List<Zone> zones = repository.findAll();
        List<GetZoneResponse> responses = zones.stream()
                .map(this::from)
                .collect(Collectors.toList());
        return BaseResponse.builder()
                .data(responses)
                .message("zones list correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    private GetZoneResponse from(Zone zone){
        GetZoneResponse response = new GetZoneResponse();
        response.setId(zone.getId());
        response.setName(zone.getName());
        return response;
    }

    public Zone FindAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(()-> new RuntimeException("not found"));
    }

}
