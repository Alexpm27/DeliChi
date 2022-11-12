package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Image;
import com.example.demorestaurant.entities.projections.FileProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {

    @Query(value = "select images.* from images " +
            "where images.restaurant_id = :idRestaurant and images.image_type = 'images'", nativeQuery = true)
    List<Image> listAllImagesByRestaurantId(Long idRestaurant);

    @Query(value = "select images.* from images " +
            "where images.restaurant_id = :idRestaurant and images.image_type = 'logo'", nativeQuery = true)
    Optional<Image> GetLogoImageByRestaurantId(Long idRestaurant);

    // Optional<FileProjection> getByRestaurantId
    @Query(value = "select images.* from images " +
            "where images.restaurant_id = :idRestaurant and images.image_type = 'banner'", nativeQuery = true)
    Optional<Image> GetBannerImageByRestaurantId(Long idRestaurant);



}
