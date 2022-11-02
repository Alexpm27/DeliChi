package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.services.FileServiceImpl;
import com.example.demorestaurant.services.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("uplFile")
public class FileController {

    @Autowired
    private IFileService service;

    // The @RequestParam has a section in postman, and it is not necessary to put it in the sending path
    @PostMapping
    public ResponseEntity<BaseResponse> uploadRestaurantImg(@RequestParam MultipartFile file,
                                                            @RequestParam String img_type,
                                                            @RequestParam Long idCeo,
                                                            @RequestParam Long idRestaurant) {
        BaseResponse baseResponse = service.uploadRestaurantImg(file, idCeo, idRestaurant, img_type);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("logo/{restaurant_id}")
    public ResponseEntity<BaseResponse> ListAllLogoImagesByRestaurantId(@PathVariable Long restaurant_id){
        BaseResponse baseResponse = service.ListAllLogoImagesByRestaurantId(restaurant_id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("images/{restaurant_id}")
    public ResponseEntity<BaseResponse> listAllImagesByRestaurantId(@PathVariable Long restaurant_id){
        BaseResponse baseResponse = service.listAllImagesByRestaurantId(restaurant_id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("banner/{restaurant_id}")
    public ResponseEntity<BaseResponse>  ListAllBannerImagesByRestaurantId(@PathVariable Long restaurant_id){
        BaseResponse baseResponse = service.ListAllBannerImagesByRestaurantId(restaurant_id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
