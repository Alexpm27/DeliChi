package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Reservation;
import com.example.demorestaurant.entities.projections.ReservationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    @Query(value = "select users.name, users.last_name, reservations.date, reservations.people " +
            "from reservations " +
            "inner join users on users.id = reservations.user_id "+
            "where reservations.restaurant_id = :restaurantId", nativeQuery = true)
    List<ReservationProjection> ListReservationByRestaurantId(Long restaurantId);

}
