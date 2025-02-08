package com.example.gestionePrenotazioni.runner;

import com.example.gestionePrenotazioni.enumeration.StationType;
import com.example.gestionePrenotazioni.model.Building;

import com.example.gestionePrenotazioni.model.Station;
import com.example.gestionePrenotazioni.model.User;
import com.example.gestionePrenotazioni.service.BuildingService;
import com.example.gestionePrenotazioni.service.StationService;
import com.example.gestionePrenotazioni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class ReservationRunner implements CommandLineRunner {

    @Autowired
    BuildingService buildingService;

    @Autowired
    UserService userService;

    @Autowired
    StationService stationService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running...");

        generateBuilding();
        generateUser();
        generateStation();
        makeReservation();

    }

    // metodo per la generazione dei building e il salvataggio nel db
    public void generateBuilding(){
        Building b1 = buildingService.createBuilding1();
        buildingService.saveBuilding(b1);
        Building b2 = buildingService.createBuilding2();
        buildingService.saveBuilding(b2);
    }

    // metodo per la generazione di user e il salvataggio nel db
    public void generateUser(){
        User userAdmin = userService.createUserAdmin();
        userService.saveUser(userAdmin);

        User u1 = userService.createUser();
        userService.saveUser(u1);
    }

    // metodo per la generazione di station e il salvataggio nel db
    public void generateStation(){
        for (int i = 0; i < 3; i++){
            stationService.saveStation(stationService.createStationBuilding1());
            stationService.saveStation(stationService.createStationBuilding2());
        }
    }

    public void makeReservation(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Di che tipo di postazione hai bisogno?");
        System.out.println("-1- postazione privata");
        System.out.println("-2- openspace");
        System.out.println("-3- conference room");
        String stationType = sc.nextLine();
        chooseStation(stationType);
    }

    public void chooseStation(String stationType){
        switch (stationType){
            case "1": {
                List<Station> privateStations = stationService.getStationsByType(StationType.PRIVATE);
                System.out.println("Elenco delle postazioni private: " + privateStations);
              break;
            }
            default:
                System.out.println("Inserire un valore valido");
        }
    }
}
