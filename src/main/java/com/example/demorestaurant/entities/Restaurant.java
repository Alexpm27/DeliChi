package com.example.demorestaurant.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(length = 1000)
    private String banner;

    @Column(length = 1000)
    private String logo;

    @Column(nullable = false, length = 255)
    @NotBlank
    private String name;

    @Column(nullable = false, length = 500)
    @NotBlank
    private String address;

    @Column(nullable = false, length = 500)
    @NotBlank
    private String schedule;

    @Column(nullable = false, length = 500)
    @NotBlank
    private String kitchen;

    @Column(nullable = false, unique = true)
    private Long phoneNumber;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false, length = 500)
    private String menu;

    @ManyToOne
    private Ceo ceo;

    @ManyToOne
    private Zone zone;

    @OneToMany(mappedBy = "restaurant")
    private List<Comment> comments;

    @OneToMany(mappedBy = "restaurant")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "restaurant")
    private List<Image> images;

}
