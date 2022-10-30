package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.projections.CeoProjection;
import com.example.demorestaurant.entities.projections.RestaurantProjection;
import com.example.demorestaurant.entities.projections.RestaurantProjection2;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICeoRepository extends JpaRepository<Ceo, Long> {
    @Query(value = "select restaurants.* from restaurants " +
            "where restaurants.ceo_id = :ceoId", nativeQuery = true)
    List<RestaurantProjection2> listAllRestaurantsByCeoId(Long ceoId);

    @Query(value = "select ceos.* from ceos " +
            "where ceos.email = :email", nativeQuery = true)
    CeoProjection getCeoByEmail(String email);

    /*@Query(value = "select c.id as Ceoid, c.name as CeoName, r.id as RestaurantId, r.name as RestaurantName, " +
            "r.address as RestaurantAddress, z.id as ZoneId,z.name as ZoneName, " +
            "r.phone_number as RestaurantPhoneNumber, r.kitchen as RestaurantKitchent, " +
            "r.schedule as RestaurantSchedule from zones_restaurants " +
            "inner join restaurants r on zones_restaurants.restaurant_id = r.id " +
            "inner join zones z on z.id = zones_restaurants.zone_id " +
            "inner join ceos c on r.ceo_id = c.id " +
            "where c.id = :ceoId", nativeQuery = true)
    List<RestaurantProjection> listRestaurantsFromCeoId(Long ceoId);*/
}
