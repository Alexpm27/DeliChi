package com.example.demorestaurant.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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
    private String name;

    @Column(nullable = false, length = 1000)
    private String fileUrl;

    @Column(nullable = false, length = 10)
    private String image_type;

    @ManyToOne
    private Restaurant restaurant;

}
