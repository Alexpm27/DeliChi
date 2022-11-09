package com.example.demorestaurant.services.interfaces;

import com.example.demorestaurant.controllers.dtos.request.CreateCommentRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCommentRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.entities.Comment;
import com.example.demorestaurant.entities.projections.CommentProjection;

public interface ICommentService {

    BaseResponse createComment (CreateCommentRequest request);

    BaseResponse getCommentById(Long id);

    BaseResponse listAllCommentByRestaurantId(Long restaurantId);

    BaseResponse updateComment(UpdateCommentRequest request, Long id);

    void delete(Long id);

    Comment from(CommentProjection commentProjection);

}
