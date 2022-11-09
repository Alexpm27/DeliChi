package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    BaseResponse uploadRestaurantImages(MultipartFile multipartFile, Long idCeo, Long idRestaurant, String img_type);

    BaseResponse listAllImagesByRestaurantId(Long idRestaurant);

    BaseResponse GetLogoImageByRestaurantId(Long idRestaurant);

    BaseResponse GetBannerImageByRestaurantId(Long idRestaurant);

    Boolean ValidateFileExtension(MultipartFile file);

    BaseResponse UpdateRestaurantLogo(MultipartFile multipartFile, Long idRestaurant, Long idCeo);

    BaseResponse UpdateRestaurantBanner(MultipartFile multipartFile, Long idRestaurant, Long idCeo);

    BaseResponse DeleteImage(Long idImage);
}
