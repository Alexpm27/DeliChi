package com.example.demorestaurant.services;

import com.example.demorestaurant.controllers.dtos.request.CreateUserRequest;
import com.example.demorestaurant.controllers.dtos.request.UpdateUserRequest;
import com.example.demorestaurant.controllers.dtos.responses.CreateUserResponse;
import com.example.demorestaurant.controllers.dtos.responses.GetUserResponse;
import com.example.demorestaurant.controllers.dtos.responses.UpdateUserResponse;
import com.example.demorestaurant.entities.User;
import com.example.demorestaurant.repositories.IUserRepository;
import com.example.demorestaurant.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    // INTERFACE METHODS

    @Autowired
    private IUserRepository repository;

    @Override
    public GetUserResponse getUsers(Long id) {
        User user = FindAndEnsureExists(id);
        return from_get(user);
    }

    // List all users
    @Override
    public List<GetUserResponse> userList() {
        return repository.findAll().stream().map(user -> from_get(user)).collect(Collectors.toList());
    }

    // Create a user
    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        User save = repository.save(from(request));
        return from_create(save);
    }

    // Update a user
    @Override
    public UpdateUserResponse updateUser(Long id, UpdateUserRequest request) {
        User user = FindAndEnsureExists(id);
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLast_name(request.getLast_name());
        user.setPhone_number(request.getPhone_number());

        User save = repository.save(user);

        return from_upd(save);
    }

    // Delete a user
    @Override
    public void deleteUser(Long id) {
        repository.delete(FindAndEnsureExists(id));
    }


    // METHODS THAT TRANSFORM A USER TO A RESPONSE

    // Transform a user to a CreateUserResponse
    private CreateUserResponse from_create (User user) {
        CreateUserResponse response = new CreateUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setLast_name(user.getLast_name());
        response.setPhone_number(user.getPhone_number());
        return response;
    }


    // Transform a user to a GetUserResponse
    private GetUserResponse from_get (User user) {
        GetUserResponse response = new GetUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setLast_name(user.getLast_name());
        response.setPhone_number(user.getPhone_number());
        return response;
    }

    // Transform a user to a UpdateUserResponse
    private UpdateUserResponse from_upd (User user){
        UpdateUserResponse response = new UpdateUserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setName(user.getName());
        response.setLast_name(user.getLast_name());
        response.setPhone_number(user.getPhone_number());
        return response;
    }


    // methods that transform a request to a user

    // Transform a CreateUserRequest to a user and return a user
    private User from(CreateUserRequest request) {
        User user = new User();
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setLast_name(request.getLast_name());
        user.setPhone_number(request.getPhone_number());
        user.setPassword(request.getPassword());
        return user;
    }

    // Other methods
    private User FindAndEnsureExists(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("ID NOT FOUND"));
    }

}
