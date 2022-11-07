package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    BaseResponse uploadRestaurantImg(MultipartFile multipartFile, Long idCeo, Long idRestaurant, String img_type);

    BaseResponse listAllImagesByRestaurantId(Long restaurant_id);

    BaseResponse ListAllLogoImagesByRestaurantId(Long restaurant_id);

    BaseResponse ListAllBannerImagesByRestaurantId(Long restaurant_id);

    Boolean ValidateFileExtension(MultipartFile file);

}
