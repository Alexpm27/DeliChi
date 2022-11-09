package com.example.demorestaurant.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false)
    @NotBlank
    private String date;

    @Column(length = 500)
    private String content;

    @Column(nullable = false)
    private Integer score;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;

}
