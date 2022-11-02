package com.example.demorestaurant.controllers.dtos.responses;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class GetImageResponse {
    private Long id;
    private String file_Url;
    private String name;
    private String img_type;
}
