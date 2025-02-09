package com.example.gestionePrenotazioni.configuration;

import com.example.gestionePrenotazioni.enumeration.StationType;
import com.example.gestionePrenotazioni.model.Building;
import com.example.gestionePrenotazioni.model.Reservation;
import com.example.gestionePrenotazioni.model.Station;
import com.example.gestionePrenotazioni.model.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.time.LocalDate;
import java.util.Locale;
import java.util.Random;

@Configuration
@PropertySource("application.properties")

public class ReservationConfig {

    Faker fk = new Faker(new Locale("it"));

    // parametri globali per creare uno user admin
    @Value("${user.admin.username}") private String adminUsername;
    @Value("${user.admin.name}") private String adminName;
    @Value("${user.admin.lastname}") private String adminLastName;
    @Value("${user.admin.email}") private String adminEmail;

    // building
    @Bean(name = "building1")
    @Scope("singleton")
    public Building building1Bean(){
        return new Building(fk.address().firstName(), fk.address().streetAddress(), fk.address().cityName());
    }

    @Bean(name = "building2")
    @Scope("singleton")
    public Building building2Bean(){
        return new Building(fk.address().firstName(), fk.address().streetAddress(), fk.address().cityName());
    }

    // station
    @Bean(name = "stationBuilding1")
    @Scope("prototype")
    public Station stationBuilding1Bean(@Qualifier("building1") Building building){
        return new Station(fk.address().streetAddress(), StationType.values()[new Random().nextInt(StationType.values().length)], (int) (1 + (Math.random() * 10)), building);
    }

    @Bean(name = "stationBuilding2")
    @Scope("prototype")
    public Station stationBuilding2Bean(@Qualifier("building2") Building building){
        return new Station(fk.address().streetAddress(), StationType.values()[new Random().nextInt(StationType.values().length)], (int) (1 + (Math.random() * 10)), building);
    }

    // user
    @Bean(name = "userAdmin")
    @Scope("singleton")
    public User adminUserBean(){
        return new User(adminUsername, adminName, adminLastName, adminEmail);
    }

    @Bean(name = "user")
    @Scope("prototype")
    public User userBean(){
        return new User(fk.name().username(), fk.name().name(), fk.name().lastName(), fk.internet().emailAddress());
    }

}
