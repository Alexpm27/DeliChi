package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {

}
