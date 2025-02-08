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
    @Qualifier("building1")
    ObjectProvider<Building> building1Provider;

    @Autowired
    @Qualifier("building2")
    ObjectProvider<Building> building2Provider;

    @Autowired
    BuildingDAORepository buildingDAO;

    public Building createBuilding1(){
        return building1Provider.getObject();
    }

    public Building createBuilding2(){
        return building2Provider.getObject();
    }

    public void saveBuilding(Building b){
        buildingDAO.save(b);
    }
}
