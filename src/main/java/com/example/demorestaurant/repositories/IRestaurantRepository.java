package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {
}
