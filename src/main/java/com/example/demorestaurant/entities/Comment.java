package com.example.demorestaurant.entities;

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
    @Column(name = "id")
    private Long id;

    @Column(name = "date")
    @NotNull
    @NotBlank
    private Date date;

    @Column(name = "content")
    private String content;

    @Column(name = "score")
    @NotNull
    @NotBlank
    private Integer score;

    @ManyToOne
    private User user;

    @ManyToOne
    private Restaurant restaurant;
}
