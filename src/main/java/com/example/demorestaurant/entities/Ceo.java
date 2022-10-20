package com.example.demorestaurant.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "ceos")
public class Ceo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotNull
    @NotBlank
    private String name;

    @Column(name = "last_name")
    @NotNull
    @NotBlank
    private String last_name;

    @Column(name = "email")
    @NotNull
    @NotBlank
    @Email
    private String email;

    @Column(name = "passwword")
    @NotNull
    @NotBlank
    private String password;

    @Column(name = "phone_number")
    @NotNull
    @NotBlank
    private Long phone_number;

    @OneToMany(mappedBy = "ceo")
    private List<Restaurant> restaurants;

}
