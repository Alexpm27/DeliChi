package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.pivotes.ZoneRestaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IZoneRestaurantRepository extends JpaRepository<ZoneRestaurant, Long> {
}
