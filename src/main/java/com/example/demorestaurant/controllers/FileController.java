package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.services.FileServiceImpl;
import com.example.demorestaurant.services.interfaces.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("file")
public class FileController {

    @Autowired
    private IFileService service;

    @PostMapping
    public ResponseEntity<BaseResponse> uploadRestaurantImg(@RequestParam MultipartFile file,
                                                            @RequestParam String imgType,
                                                            @RequestParam Long idCeo,
                                                            @RequestParam Long idRestaurant) {
        BaseResponse baseResponse = service.uploadRestaurantImages(file, idCeo, idRestaurant, imgType);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("updateLogo")
    public ResponseEntity<BaseResponse> UpdateRestaurantLogo(@RequestParam MultipartFile file,
                                                             @RequestParam Long idCeo,
                                                             @RequestParam Long idRestaurant) {
        BaseResponse baseResponse = service.UpdateRestaurantLogo(file, idCeo, idRestaurant);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("updateBanner")
    public ResponseEntity<BaseResponse> UpdateRestaurantBanner(@RequestParam MultipartFile file,
                                                               @RequestParam Long idCeo,
                                                               @RequestParam Long idRestaurant) {
        BaseResponse baseResponse = service.UpdateRestaurantBanner(file, idCeo, idRestaurant);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("deleteImage/{idImage}")
    public ResponseEntity<BaseResponse> DeleteImage(@PathVariable Long idImage){
        BaseResponse baseResponse = service.DeleteImage(idImage);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("getLogo/{idRestaurant}")
    public ResponseEntity<BaseResponse> GetLogoImageByRestaurantId(@PathVariable Long idRestaurant){
        BaseResponse baseResponse = service.GetLogoImageByRestaurantId(idRestaurant);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("getBanner/{idRestaurant}")
    public ResponseEntity<BaseResponse>  GetBannerImageByRestaurantId(@PathVariable Long idRestaurant){
        BaseResponse baseResponse = service.GetBannerImageByRestaurantId(idRestaurant);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("ListImages/{idRestaurant}")
    public ResponseEntity<BaseResponse> listAllImagesByRestaurantId(@PathVariable Long idRestaurant){
        BaseResponse baseResponse = service.listAllImagesByRestaurantId(idRestaurant);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}
