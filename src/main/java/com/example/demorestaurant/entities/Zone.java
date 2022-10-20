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
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @OneToMany(mappedBy = "zone")
    private List<ZoneRestaurant> zoneRestaurants;
}
