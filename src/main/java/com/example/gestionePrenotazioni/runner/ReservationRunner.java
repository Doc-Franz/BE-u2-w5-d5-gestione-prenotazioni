package com.example.gestionePrenotazioni.runner;

import com.example.gestionePrenotazioni.model.Building;
import com.example.gestionePrenotazioni.model.User;
import com.example.gestionePrenotazioni.service.BuildingService;
import com.example.gestionePrenotazioni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReservationRunner implements CommandLineRunner {

    @Autowired
    BuildingService buildingService;

    @Autowired
    UserService userService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running...");

        generateBuilding();

    }

    // metodo per la generazione dei building e il salvataggio nel db
    public void generateBuilding(){
        Building b1 = buildingService.createBuilding();
        buildingService.saveBuilding(b1);
    }

    public void generateUser(){
        User userAdmin = userService.createUserAdmin();
        userService.saveUser(userAdmin);

        User u1 = userService.createUser();
        userService.saveUser(u1);


    }
}
