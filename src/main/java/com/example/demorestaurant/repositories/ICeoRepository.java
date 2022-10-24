package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.projections.RestaurantProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICeoRepository extends JpaRepository<Ceo, Long> {
    @Query(value = "select ceos.id, ceos.name, restaurants.id, restaurants.name, restaurants.address, " +
            "zones.*, restaurants.phone_number, restaurants.kitchen, restaurants.schedule from ceos " +
            "inner join restaurants on ceos.id = restaurants.ceo_id " +
            "inner join zones_restaurants on restaurants.id = zones_restaurants.restaurant_id " +
            "inner join zones on zones.id = zones_restaurants.zone_id " +
            "where ceos.id = :ceoId", nativeQuery = true)
    List<RestaurantProjection> listAllRestaurantsByCeoId(Long ceoId);
}
