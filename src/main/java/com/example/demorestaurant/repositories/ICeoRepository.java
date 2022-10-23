package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Ceo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICeoRepository extends JpaRepository<Ceo, Long> {

}
