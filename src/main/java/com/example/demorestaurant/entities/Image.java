package com.example.demorestaurant.entities;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

// This clas will be used when we need to upload to the database
@Entity
@Getter
@Setter
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 1000)
    @NotBlank
    private String name;

    @Column(nullable = false, length = 1000)
    private String fileUrl;

    @Column(nullable = false, length = 10)
    @NotBlank
    private String imageType;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToOne
    private Ceo ceo;

}
