package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.projections.RestaurantProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IRestaurantRepository extends JpaRepository<Restaurant, Long> {


    @Query(value = "select c.id as Ceoid, c.name as CeoName, r.id as RestaurantId, r.name as RestaurantName, " +
            "r.address as RestaurantAddress, z.id as ZoneId,z.name as ZoneName, " +
            "r.phone_number as RestaurantPhoneNumber, r.kitchen as RestaurantKitchent, " +
            "r.schedule as RestaurantSchedule from zones_restaurants " +
            "inner join restaurants r on zones_restaurants.restaurant_id = r.id " +
            "inner join zones z on z.id = zones_restaurants.zone_id " +
            "inner join ceos c on r.ceo_id = c.id " +
            "where zones_restaurants.restaurant_id = :restaurantId", nativeQuery = true)
    RestaurantProjection getRestaurantByRestaurantId(Long restaurantId);

    /*@Query(value = "select ceos.id, ceos.name, restaurants.id, restaurants.name, restaurants.address, zones.*, restaurants.phone_number, restaurants.kitchen, restaurants.schedule from restaurants " +
            "inner join zones_restaurants on zones_restaurants.id = restaurants.id " +
            "inner join ceos on ceos.id = restaurants.ceo_id " +
            "inner join zones on zones.id = zones_restaurants.zone_id " +
            "where restaurants.id = :restaurantId", nativeQuery = true)
    RestaurantProjection getRestaurantByRestaurantId(Long restaurantId);*/

    /*@Query(value = "select ceos.id, ceos.name, restaurants.id, restaurants.name, restaurants.address, zones.*, restaurants.phone_number, restaurants.kitchen, restaurants.schedule from restaurants " +
            "inner join ceos on ceos.id = restaurants.ceo_id " +
            "inner join zones_restaurants on zones_restaurants.restaurant_id = restaurants.id " +
            "inner join zones on zones.id = zones_restaurants.zone_id", nativeQuery = true)
    List<RestaurantProjection> listAllRestaurants();*/

    @Query(value = "select c.id as Ceoid, c.name as CeoName, r.id as RestaurantId, r.name as RestaurantName, " +
            "r.address as RestaurantAddress, z.id as ZoneId,z.name as ZoneName, " +
            "r.phone_number as RestaurantPhoneNumber, r.kitchen as RestaurantKitchent, " +
            "r.schedule as RestaurantSchedule from zones_restaurants " +
            "inner join restaurants r on zones_restaurants.restaurant_id = r.id " +
            "inner join zones z on z.id = zones_restaurants.zone_id " +
            "inner join ceos c on r.ceo_id = c.id ", nativeQuery = true)
    List<RestaurantProjection> listAllRestaurants();
}
