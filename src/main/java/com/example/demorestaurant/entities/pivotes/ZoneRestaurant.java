package com.example.demorestaurant.entities.pivotes;

import com.example.demorestaurant.entities.Restaurant;
import com.example.demorestaurant.entities.Zone;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "zones_restaurants")
@Getter
@Setter
public class ZoneRestaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    private Zone zone;

    @ManyToOne
    private Restaurant restaurant;
}
