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
@Data

public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private StationType stationType;
    private int numMaxUsers;

    // più postazioni associate ad un unico building
    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    private String city;

    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL)
    List<Reservation> reservationList;

    public Station(){
        this.reservationList = new ArrayList<Reservation>();
    }

    public Station(String name, StationType stationType, int numMaxUsers, Building building) {
        this.name = name;
        this.stationType = stationType;
        this.numMaxUsers = numMaxUsers;
        this.building = building;
        this.reservationList = new ArrayList<Reservation>();
        this.city = building.getCity();
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stationType=" + stationType +
                ", numMaxUsers=" + numMaxUsers +
                ", building=" + building.getId() +
                ", city=" + city +
                '}';
    }
}
