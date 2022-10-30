package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.services.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/*
    If you need to implement another text in the path, you can do it
 */
@RestController
@RequestMapping("uplFile")
public class FileController {

    @Autowired
    private FileServiceImpl service;

    // The @RequestParam has a section in postman, and it is not necessary to put it in the sending path
    @PostMapping()
    public ResponseEntity<BaseResponse> upload(@RequestParam MultipartFile file, @RequestParam Long idCeo, @RequestParam Long idRestaurant ) {
        BaseResponse baseResponse = service.uploadRestaurantImg(file, idCeo, idRestaurant);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
