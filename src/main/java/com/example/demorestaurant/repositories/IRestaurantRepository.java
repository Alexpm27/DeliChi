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

    @Query(value = "select restaurants.id, restaurants.logo, restaurants.name, zones.name zone " +
            "from restaurants " +
            "inner join ceos c on restaurants.ceo_id = c.id " +
            "inner join zones on restaurants.zone_id = zones.id " +
            "where c.id = :ceoId", nativeQuery = true)
    List<ResturantProjection> listAllRestaurantsByCeoId(Long ceoId);

    Optional<List<ResturantProjection>> getAllByCeo_Id(Long id);

    @Query(value = "select restaurants.id, restaurants.banner, restaurants.logo, restaurants.name, " +
            "restaurants.address, restaurants.phone_number, " +
            "restaurants.schedule, restaurants.kitchen, zones.name zone from restaurants " +
            "inner join zones on restaurants.zone_id = zones.id " +
            "where restaurants.id = :restaurantId", nativeQuery = true)
    RestaurantByResturantIdProyection getRestaurantByRestaurantId(Long restaurantId);

    @Query(value = "select restaurants.id, restaurants.logo, restaurants.name, zones.name zone " +
            "from restaurants " +
            "inner join zones on restaurants.zone_id = zones.id " +
            "where restaurants.name = :name", nativeQuery = true)
    List<ResturantProjection> listAllRestaurantsByName(String name);

    @Query(value = "select images.file_url images from images " +
            "where images.restaurant_id = :restaurantId", nativeQuery = true)
    List<ImageProection> listAllImages(Long restaurantId);

    @Query(value = "select comments.content, comments.score, comments.date " +
            "from comments where restaurant_id = :restaurantId", nativeQuery = true)
    List<CommentProjection> listAllComments(Long restaurantId);


}
