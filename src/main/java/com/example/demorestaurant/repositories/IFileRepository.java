package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Image;
import com.example.demorestaurant.entities.projections.FileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFileRepository extends JpaRepository<Image, Long> {


    @Query(value = "select images.* from images " +
            "where images.restaurant_id = :restaurant_id and images.image_type = 'images'", nativeQuery = true)
    List<FileProjection> listAllImagesByRestaurantId(Long restaurant_id);

    @Query(value = "select images.* from images " +
            "where images.restaurant_id = :restaurant_id and images.image_type = 'logo'", nativeQuery = true)
    List<FileProjection> ListAllLogoImagesByRestaurantId(Long restaurant_id);

    @Query(value = "select images.* from images " +
            "where images.restaurant_id = :restaurant_id and images.image_type = 'banner'", nativeQuery = true)
    List<FileProjection> ListAllBannerImagesByRestaurantId(Long restaurant_id);

}
