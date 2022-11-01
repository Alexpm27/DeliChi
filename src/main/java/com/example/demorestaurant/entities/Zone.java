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
@Table(name = "zones")
public class Zone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 255)
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "zone")
    private List<ZoneRestaurant> zoneRestaurants;

}
