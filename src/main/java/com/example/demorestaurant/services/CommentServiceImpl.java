package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateCommentRequest;
import com.example.demorestaurant.controllers.dtos.request.GetCommentRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCommentRequest;
import com.example.demorestaurant.controllers.dtos.responses.BaseResponse;
import com.example.demorestaurant.controllers.dtos.responses.CreateCommentResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetCommentResponse;
import com.example.demorestaurant.entities.Comment;
import com.example.demorestaurant.repositories.ICommentRepository;
import com.example.demorestaurant.services.interfaces.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository repository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private RestaurantServiceImpl restaurantService;

    @Override
    public BaseResponse createComment(CreateCommentRequest request) {
        return BaseResponse.builder()
                .data(from(repository.save(from(request)), request)) // Returning the saved user and request
                .message("Comment created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse getCommentById(Long id) {
        return BaseResponse.builder()
                .data(from_get(FindAndEnsureExist(id)))
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse listAllCommentByRestaurantId(Long restaurantId) {
        return BaseResponse.builder().build();
    }

    @Override
    public BaseResponse updateComment(UpdateCommentRequest request, Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    private Comment from (CreateCommentRequest request){
        Comment comment = new Comment();
        comment.setUser(userService.FindAndEnsureExists(request.getUser_id()));
        comment.setRestaurant(restaurantService.FindRestaurantAndEnsureExist(request.getRestaurant_id()));
        comment.setDate(request.getDate());
        comment.setContent(request.getContent());
        comment.setScore(request.getScore());
        return comment;
    }

    private CreateCommentResponse from (Comment comment, CreateCommentRequest request){
        CreateCommentResponse response = new CreateCommentResponse();

        response.setId(comment.getId());
        response.setDate(comment.getDate());
        response.setContent(comment.getContent());
        response.setScore(comment.getScore());

        // This response need to return a Long id, but the methods in the injection only return a User, so I decided to return with the request methods
        // We can use the request id because this method only be used when we are sure about the request data. I think so

        // Supestamente ya quedo, por cualquier error, aqui esta el responsable >:3
        response.setRestaurant_id(comment.getRestaurant().getId());
        response.setUser_id(comment.getUser().getId());

        return response;
    }

    private GetCommentResponse from_get(Comment comment){
        GetCommentResponse response = new GetCommentResponse();

        response.setId(comment.getId());
        response.setDate(comment.getDate());
        response.setContent(comment.getContent());
        response.setScore(comment.getScore());

        // Supestamente ya quedo, por cualquier error, aqui esta el responsable >:3
        response.setRestaurant_id(comment.getRestaurant().getId());
        response.setUser_id(comment.getUser().getId());
        return response;
    }

    private Comment FindAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Not found"));
    }

}
