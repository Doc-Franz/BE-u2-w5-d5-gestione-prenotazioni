package com.example.gestionePrenotazioni.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "buildings")
@Data

public class Building {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String address;
    private String city;

    @OneToMany(mappedBy = "building")
    List<Station> stationList;

    public Building(){
        this.stationList = new ArrayList<Station>();
    }

    public Building(String name, String address, String city) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.stationList = new ArrayList<Station>();
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }
}
