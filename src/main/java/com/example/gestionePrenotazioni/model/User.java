package com.example.gestionePrenotazioni.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@Data

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false, unique = true)
    private String userName;
    private String name;
    private String lastName;
    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<Reservation> reservationList;

    public User(String userName, String name, String lastName, String email) {
        this.userName = userName;
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.reservationList = new ArrayList<Reservation>();
    }
}
