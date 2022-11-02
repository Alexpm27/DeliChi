package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Ceo;
import com.example.demorestaurant.entities.projections.CeoProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ICeoRepository extends JpaRepository<Ceo, Long> {

    @Query(value = "select ceos.* from ceos " +
            "where ceos.email = :email", nativeQuery = true)
    CeoProjection getCeoByEmail(String email);

    @Query(value = "select ceos.email, ceos.phone_number from ceos " +
            "where ceos.email = :email or ceos.phone_number = :phoneNumber", nativeQuery = true)
    CeoProjection findEmailOrPhoneNumberExists(String email, Long phoneNumber);

}
