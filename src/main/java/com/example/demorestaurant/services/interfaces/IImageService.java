package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetImageResponse;
import com.example.demorestaurant.entities.Image;
import com.example.demorestaurant.entities.projections.FileProjection;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IImageService {

    BaseResponse uploadRestaurantImages(MultipartFile multipartFile, Long idCeo, Long idRestaurant);

    BaseResponse uploadRestaurantLogoImage(MultipartFile multipartFile, Long idCeo, Long idRestaurant);

    BaseResponse uploadRestaurantBannerImage(MultipartFile multipartFile, Long idCeo, Long idRestaurant);

    BaseResponse listAllImagesByRestaurantId(Long idRestaurant);

    BaseResponse GetLogoImageByRestaurantId(Long idRestaurant);

    BaseResponse GetBannerImageByRestaurantId(Long idRestaurant);

    Boolean ValidateFileExtension(MultipartFile file);

    BaseResponse UpdateRestaurantLogo(MultipartFile multipartFile, Long idRestaurant, Long idCeo);

    BaseResponse UpdateRestaurantBanner(MultipartFile multipartFile, Long idRestaurant, Long idCeo);

    BaseResponse DeleteImage(Long idImage);

    Image fromFileProjectionToImage(FileProjection projection);

}
