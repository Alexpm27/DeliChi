package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    BaseResponse uploadRestaurantImg(MultipartFile multipartFile, Long idCeo, Long idRestaurant);
}
