package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Reservation;
import com.example.demorestaurant.entities.projections.ReservationProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Long> {

    Optional<List<ReservationProjection>> findAllByRestaurant_Id(Long restaurantId);

}
