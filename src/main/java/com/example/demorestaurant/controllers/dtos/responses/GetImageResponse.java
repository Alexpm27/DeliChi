package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GetImageResponse {
    private Long id;
    private String fileUrl;
    private String imageType;
}
