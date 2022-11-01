package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.request.CreateCommentRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private ICommentService service;

    @PostMapping
    public ResponseEntity<BaseResponse> createComment(@RequestBody CreateCommentRequest request){
        BaseResponse baseResponse = service.createComment(request);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> getCommentById(@PathVariable Long id){
        BaseResponse baseResponse = service.getCommentById(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @GetMapping("restaurant/{restaurantId}/comments")
    public ResponseEntity<BaseResponse> listAllCommentByRestaurantId(@PathVariable Long restaurantId){
        BaseResponse baseResponse = service.listAllCommentByRestaurantId(restaurantId);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> updateComment

}
