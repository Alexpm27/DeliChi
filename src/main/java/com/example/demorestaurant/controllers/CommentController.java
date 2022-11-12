package com.example.demorestaurant.controllers;

import com.example.demorestaurant.controllers.dtos.request.CreateCommentRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCommentRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("comment")
public class CommentController {

    @Autowired
    private ICommentService service;

    @GetMapping("{id}")
    public ResponseEntity<BaseResponse> get(@Valid @PathVariable Long id){
        BaseResponse baseResponse = service.get(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @PostMapping("user/{userId}/restaurant/{restaurantId}")
    public ResponseEntity<BaseResponse> create(@Valid @RequestBody CreateCommentRequest request,
                                               @Valid @PathVariable Long userId,
                                               @Valid @PathVariable Long restaurantId){
        BaseResponse baseResponse = service.create(request, userId, restaurantId);
        return new ResponseEntity<>(baseResponse,baseResponse.getHttpStatus());
    }

    @PutMapping("{id}")
    public ResponseEntity<BaseResponse> update(@Valid @RequestBody UpdateCommentRequest request, @Valid @PathVariable Long id){
        BaseResponse baseResponse = service.update(request, id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<BaseResponse> delete(@Valid @PathVariable Long id){
        BaseResponse baseResponse = service.delete(id);
        return new ResponseEntity<>(baseResponse, baseResponse.getHttpStatus());
    }

}