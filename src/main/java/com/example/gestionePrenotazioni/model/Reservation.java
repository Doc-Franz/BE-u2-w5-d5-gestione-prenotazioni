package com.example.gestionePrenotazioni.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "reservations")
@Data

public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private LocalDate reservationDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;

    public Reservation(){}

    public Reservation(LocalDate reservationDate, User user, Station station) {
        this.reservationDate = reservationDate;
        this.user = user;
        this.station = station;
    }

}
