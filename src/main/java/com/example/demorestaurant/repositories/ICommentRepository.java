package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Comment;
import com.example.demorestaurant.entities.projections.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    // Aquí agrega el query @Alan xfa, que obtenga todos los comentarios de un restaurante
    List<CommentProjection> listAllCommentsByRestaurantId(Long id);
}
