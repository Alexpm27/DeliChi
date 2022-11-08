package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    BaseResponse uploadRestaurantImg(MultipartFile multipartFile, Long idCeo, Long idRestaurant, String img_type);

    BaseResponse listAllImagesByRestaurantId(Long idRestaurant);

    BaseResponse ListAllLogoImagesByRestaurantId(Long idRestaurant);

    BaseResponse ListAllBannerImagesByRestaurantId(Long idRestaurant);

    Boolean ValidateFileExtension(MultipartFile file);

    String UpdateRestaurantLogo(MultipartFile file, Long idRestaurant, Long idCeo);
}
