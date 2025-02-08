package com.example.gestionePrenotazioni.configuration;

import com.example.gestionePrenotazioni.enumeration.StationType;
import com.example.gestionePrenotazioni.model.Building;
import com.example.gestionePrenotazioni.model.Station;
import com.github.javafaker.Faker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource("application.properties")

public class ReservationConfig {

    Faker fk = new Faker();

    @Bean(name = "building")
    @Scope("prototype")
    public Building buildingBean(){
        return new Building(fk.address().firstName(), fk.address().streetAddress(), fk.address().cityName());
    }

    @Bean(name = "station")
    @Scope("prototype")
    public Station stationBean(){
        return new Station(fk.address().streetAddress(), StationType.OPENSPACE, 5, buildingBean());
    }

}
