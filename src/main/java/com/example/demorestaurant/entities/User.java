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
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 255)
    @NotBlank
    private String name;

    @Column(nullable = false, length = 255)
    @NotBlank
    private String last_name;

    @Column(nullable = false, unique = true, length = 255)
    @NotBlank
    @Email
    private String email;

    @Column(nullable = false, length = 255)
    @NotBlank
    private String password;

    @Column(nullable = false, unique = true)

    private Long phone_number;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "user")
    private List<Reservation> reservations;

}
