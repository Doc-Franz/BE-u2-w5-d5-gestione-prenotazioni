package com.example.gestionePrenotazioni.model;

import com.example.gestionePrenotazioni.enumeration.StationType;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stations")
@NoArgsConstructor
@Data

public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private StationType stationType;
    private int numMaxUsers;
    @Column(nullable = true)
    private LocalDate reservationDate;

    // più postazioni associate ad un unico building
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    List<Reservation> reservationList;

    public Station(String name, StationType stationType, int numMaxUsers, Building building) {
        this.name = name;
        this.stationType = stationType;
        this.numMaxUsers = numMaxUsers;
        this.building = building;
        this.reservationList = new ArrayList<Reservation>();
        this.reservationDate = null;
    }
}
