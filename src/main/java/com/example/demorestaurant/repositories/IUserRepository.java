package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.User;
import com.example.demorestaurant.entities.projections.UserReservationsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    /*ESTE METODO NO SE PUEDE CAMBIAR POR QUE QUIERES LA LISTA DE RESERVACIONES DE UN USER
    * Y JPA PARA FUNCIONAR UTILIZA LA ENTIDAD QUE PASAS COMO PARAMETRO ARRIBA ENTONCES
    * PARA PODER HACERLO ES NECESARIO HACER ESTE METODO EN SU PROPIO REPOSITORIO
    * POR QUE VAS A OBTENER TODAS LAS RESERVACIONES HECHAS POR UN USER*/

    @Query(value = "select restaurants.name, reservations.date, reservations.people from reservations\n" +
            "inner join restaurants on reservations.restaurant_id = restaurants.id\n" +
            "where reservations.user_id = :userId", nativeQuery = true)
    List<UserReservationsProjection> ListReservationsByUserId(Long userId) ;
}
