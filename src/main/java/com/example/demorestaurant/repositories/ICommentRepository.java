package com.example.demorestaurant.repositories;

import com.example.demorestaurant.entities.Comment;
import com.example.demorestaurant.entities.projections.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ICommentRepository extends JpaRepository<Comment, Long> {

    Optional<List<CommentProjection>> findAllByRestaurant_Id(Long id);

}
