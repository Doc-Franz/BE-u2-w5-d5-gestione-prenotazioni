package com.example.gestionePrenotazioni.service;

import com.example.gestionePrenotazioni.model.Building;
import com.example.gestionePrenotazioni.repository.BuildingDAORepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BuildingService {

    @Autowired
    @Qualifier("building")
    ObjectProvider<Building> buildingProvider;

    @Autowired
    BuildingDAORepository buildingDAO;

    public Building createBuilding(){
        return buildingProvider.getObject();
    }

    public void saveBuilding(Building b){
        buildingDAO.save(b);
    }
}
