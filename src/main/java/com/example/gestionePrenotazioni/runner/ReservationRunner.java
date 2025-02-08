package com.example.gestionePrenotazioni.runner;

import com.example.gestionePrenotazioni.model.Building;
import com.example.gestionePrenotazioni.service.BuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ReservationRunner implements CommandLineRunner {

    @Autowired
    BuildingService buildingService;

    @Override
    public void run(String... args) throws Exception {
        Building b1 = buildingService.createBuilding();
        System.out.println("Running...");
        buildingService.saveBuilding(b1);
    }
}
