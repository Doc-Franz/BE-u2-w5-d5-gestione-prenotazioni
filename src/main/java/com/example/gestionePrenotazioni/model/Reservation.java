package com.example.gestionePrenotazioni.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDate reservationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;
}
