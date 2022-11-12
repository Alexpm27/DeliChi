package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.projections.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {

    @Query(value = "select r.* "+
            "from restaurants r", nativeQuery = true)
    Optional<List<Restaurant>> findAllRestaurants();

    Optional<List<Restaurant>> findAllByName(String name);

}