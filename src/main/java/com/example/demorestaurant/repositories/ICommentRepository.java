package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Comment;
import com.example.demorestaurant.entities.projections.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    @Query(value = "select comments.* from comments " +
            "where comments.restaurants_id = :id", nativeQuery = true)
    List<CommentProjection> listAllCommentsByRestaurantId(Long id);

}
