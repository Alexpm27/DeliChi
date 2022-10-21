package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<User, Long> {
}
