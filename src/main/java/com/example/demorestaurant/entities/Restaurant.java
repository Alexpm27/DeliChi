package com.example.demorestaurant.entities;

import com.example.demorestaurant.entities.pivotes.ZoneRestaurant;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "address")
    @NotNull
    @NotBlank
    private String address;

    @Column(name = "schedule")
    @NotNull
    @NotBlank
    private String schedule;

    @Column(name = "kitchen")
    @NotNull
    @NotBlank
    private String kitchen;

    @Column(name = "phone_number")
    @NotNull
    @NotBlank
    private Long phone_number;

    @ManyToOne
    private Ceo ceo;

    @OneToMany(mappedBy = "restaurant")
    private List<Comment> comments;

    @OneToMany(mappedBy = "restaurant")
    private List<ZoneRestaurant> restaurantZones;

    @OneToMany(mappedBy = "restaurant")
    private List<Reservation> reservations;
}
