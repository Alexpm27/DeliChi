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

    Optional<List<RestaurantProjection>> findAllByCeo_Id(Long id);

    Optional<List<Restaurant>> getRestaurantByCeo_Id(Long id);

    Optional<List<RestaurantProjection>> findAllByName(String name);

    @Query(value = "select images.* from images " +
            "where images.restaurant_id = :restaurantId", nativeQuery = true)
    Optional<List<FileProjection>> listAllImagesByRestaurantId(Long restaurantId);

    @Query(value = "select comments.* from comments " +
            "where comments.restaurant_id = :restaurantId", nativeQuery = true)
    Optional<List<CommentProjection>> listAllCommentsByRestaurantId(Long restaurantId);

    @Query(value = "select reservations.* from reservations " +
            "where reservations.restaurant_id = :restaurantId", nativeQuery = true)
    Optional<List<ReservationProjection>> listAllReservationsByRestaurantId(Long restaurantId);

    Optional<List<RestaurantProjection>> findAllByZone_Id(Long id);

}