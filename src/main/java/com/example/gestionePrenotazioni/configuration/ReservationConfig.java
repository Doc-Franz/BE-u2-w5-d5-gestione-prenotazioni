package com.example.gestionePrenotazioni.configuration;

import com.example.gestionePrenotazioni.enumeration.StationType;
import com.example.gestionePrenotazioni.model.Building;
import com.example.gestionePrenotazioni.model.Station;
import com.example.gestionePrenotazioni.model.User;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

import java.util.Locale;

@Configuration
@PropertySource("application.properties")

public class ReservationConfig {

    Faker fk = new Faker(new Locale("it"));

    // parametri globali per creare uno user admin
    @Value("${user.admin.username}") private String adminUsername;
    @Value("${user.admin.name}") private String adminName;
    @Value("${user.admin.lastname}") private String adminLastName;
    @Value("${user.admin.email}") private String adminEmail;

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
