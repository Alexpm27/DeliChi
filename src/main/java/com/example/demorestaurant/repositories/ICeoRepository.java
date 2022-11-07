package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.projections.CeoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICeoRepository extends JpaRepository<Ceo, Long> {

    /*@Query(value = "select ceos.* from ceos " +
            "where ceos.email = :email", nativeQuery = true)
    CeoProjection getCeoByEmail(String email);*/

    /*@Query(value = "select ceos.email, ceos.phone_number from ceos " +
            "where ceos.email = :email or ceos.phone_number = :phoneNumber", nativeQuery = true)
    Optional<CeoProjection> findEmailOrPhoneNumberExists(String email, Long phoneNumber);*/

    Optional<CeoProjection> findByEmailOrPhoneNumber(String email, Long phoneNumber);

    //Optional<Ceo> findByPhoneNumber(Long phoneNumber);

    Optional<CeoProjection> findCeoByEmail(String email);

    //CeoProjection getCeoByEmail(String email);

   // Optional<CeoProjection> getCeoByEmail(String email);
}
