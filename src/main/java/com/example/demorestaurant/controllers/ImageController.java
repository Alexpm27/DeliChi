package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.services.interfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private IImageService service;

    @PostMapping("ceo/{ceoId}/restaurant/{restaurantId}/image")
    public ResponseEntity<BaseResponse> uploadRestaurantImg(@RequestParam MultipartFile file,
                                                            @PathVariable Long ceoId,
                                                            @PathVariable Long restaurantId) {
        BaseResponse baseResponse = service.uploadRestaurantImages(file, ceoId, restaurantId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("ceo/{ceoId}/restaurant/{restaurantId}/logo")
    public ResponseEntity<BaseResponse> uploadLogoImg(@RequestParam MultipartFile file,
                                                      @PathVariable Long ceoId,
                                                      @PathVariable Long restaurantId){
        BaseResponse baseResponse = service.uploadRestaurantLogoImage(file, ceoId, restaurantId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("ceo/{ceoId}/restaurant/{restaurantId}/banner")
    public ResponseEntity<BaseResponse> uploadBannerImg(@RequestParam MultipartFile file,
                                                        @PathVariable Long ceoId,
                                                        @PathVariable Long restaurantId){
        BaseResponse baseResponse = service.uploadRestaurantBannerImage(file, ceoId, restaurantId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("ceo/{ceoId}/restaurant/{restaurantId}/updateLogo")
    public ResponseEntity<BaseResponse> UpdateRestaurantLogo(@RequestParam MultipartFile file,
                                                             @PathVariable Long ceoId,
                                                             @PathVariable Long restaurantId) {
        BaseResponse baseResponse = service.UpdateRestaurantLogo(file, restaurantId, ceoId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("ceo/{ceoId}/restaurant/{restaurantId}/updateBanner")
    public ResponseEntity<BaseResponse> UpdateRestaurantBanner(@RequestParam MultipartFile file,
                                                               @PathVariable Long ceoId,
                                                               @PathVariable Long restaurantId) {
        BaseResponse baseResponse = service.UpdateRestaurantBanner(file, restaurantId, ceoId);
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
