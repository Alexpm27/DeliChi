package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateCommentRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCommentRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.CommentResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetCommentResponse;
import com.example.demorestaurant.entities.Comment;
import com.example.demorestaurant.entities.projections.CommentProjection;

import java.util.List;

public interface ICommentService {
    BaseResponse get(Long id);

    BaseResponse create(CreateCommentRequest request, Long userId, Long restaurantId);

    BaseResponse update(UpdateCommentRequest request, Long id);

    BaseResponse delete(Long id);

    CommentResponse fromCommentToCommentResponse(Comment comment);
}
