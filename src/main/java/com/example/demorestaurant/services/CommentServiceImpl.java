package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateCommentRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateCommentRequest;
import com.example.demorestaurant.controllers.dtos.responses.*;
import com.example.demorestaurant.entities.Comment;
import com.example.demorestaurant.entities.exceptions.NotFoundException;
import com.example.demorestaurant.repositories.ICommentRepository;
import com.example.demorestaurant.services.interfaces.ICommentService;
import com.example.demorestaurant.services.interfaces.IRestaurantService;
import com.example.demorestaurant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    private ICommentRepository repository;

    @Autowired
    private IUserService userService;

    @Autowired
    private IRestaurantService restaurantService;

    @Override
    public BaseResponse get(Long id) {
        return BaseResponse.builder()
                .data(fromCommentToGetCommentResponse(findAndEnsureExist(id)))
                .message("comment by id")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse create(CreateCommentRequest request, Long userId, Long restaurantId) {
        return BaseResponse.builder()
                .data(from(repository.save(from(request, userId, restaurantId))))
                .message("Comment created correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK).build();
    }

    @Override
    public BaseResponse update(UpdateCommentRequest request, Long id) {
        return BaseResponse.builder()
                .data(fromCommentToUpdateCommentResponse(repository.save(validationUpdateDataComment(request, id))))
                .message("Comment updated correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    @Override
    public BaseResponse delete(Long id) {
        repository.delete(findAndEnsureExist(id));
        return BaseResponse.builder()
                .message("Comment Deleted Correctly")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    private Comment findAndEnsureExist(Long id){
        return repository.findById(id).orElseThrow(NotFoundException::new);
    }

    private GetCommentResponse fromCommentToGetCommentResponse(Comment comment){
        return GetCommentResponse.builder()
                .id(comment.getId())
                .date(comment.getDate())
                .content(comment.getContent())
                .score(comment.getScore())
                .restaurant(restaurantService.fromRestaurantToRestaurantResponse(comment.getRestaurant()))
                .user(userService.fromUserToUserResponse(comment.getUser())).build();
    }

    private Comment from(CreateCommentRequest request, Long userId, Long restaurantId){
        Comment comment = new Comment();
        comment.setUser(userService.findAndEnsureExists(userId));
        comment.setRestaurant(restaurantService.findAndEnsureExist(restaurantId));
        comment.setDate(request.getDate());
        comment.setContent(request.getContent());
        comment.setScore(request.getScore());
        return comment;
    }

    private CreateCommentResponse from(Comment comment){
        return CreateCommentResponse.builder()
                .id(comment.getId())
                .user(userService.fromUserToUserResponse(comment.getUser()))
                .date(comment.getDate())
                .content(comment.getContent())
                .score(comment.getScore())
                .restaurant(restaurantService.fromRestaurantToRestaurantResponse(comment.getRestaurant())).build();
    }

    private Comment validationUpdateDataComment(UpdateCommentRequest request, Long id){
        Comment comment = findAndEnsureExist(id);
        if(request.getDate().length() == 0 || request.getDate() == null || Objects.equals(request.getDate(), "")) {
            comment.setDate(comment.getDate());
        }else {
            comment.setDate(request.getDate());
        }
        if(request.getScore() == null || request.getScore() == 0) {
            comment.setScore(comment.getScore());
        }else {
            comment.setScore(request.getScore());
        }
        if(request.getContent().length() == 0 || request.getContent() == null || Objects.equals(request.getContent(), "")) {
            comment.setContent(comment.getContent());
        }else {
            comment.setContent(request.getContent());
        }
        return comment;
    }

    private UpdateCommentResponse fromCommentToUpdateCommentResponse(Comment comment){
        return UpdateCommentResponse.builder()
                .content(comment.getContent())
                .date(comment.getDate())
                .id(comment.getId())
                .restaurant(restaurantService.fromRestaurantToRestaurantResponse(comment.getRestaurant()))
                .score(comment.getScore())
                .user(userService.fromUserToUserResponse(comment.getUser())).build();
    }

    @Override
    public CommentResponse fromCommentToCommentResponse(Comment comment){
        return CommentResponse.builder()
                .date(comment.getDate())
                .content(comment.getContent())
                .score(comment.getScore())
                .build();
    }

}