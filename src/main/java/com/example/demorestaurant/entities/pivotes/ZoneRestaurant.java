package com.example.demorestaurant.entities.pivotes;

import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.Zone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@Table(name = "zones_restaurants")
public class ZoneRestaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @ManyToOne
    private Zone zone;

    @ManyToOne
    private Restaurant restaurant;

}
