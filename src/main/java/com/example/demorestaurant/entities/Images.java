package com.example.demorestaurant.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

// This clas will be used when we need to upload to the database
@Entity
@Getter @Setter
@Table(name = "images")
public class Images {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "img_name")
    private String name;

    @Column(name = "img_url")
    private String fileUrl;
}
