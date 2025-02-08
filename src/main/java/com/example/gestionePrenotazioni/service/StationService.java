package com.example.gestionePrenotazioni.service;

import com.example.gestionePrenotazioni.enumeration.StationType;
import com.example.gestionePrenotazioni.model.Building;
import com.example.gestionePrenotazioni.model.Station;
import com.example.gestionePrenotazioni.repository.StationDAORepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class StationService {

    @Autowired
    @Qualifier("stationBuilding1")
    ObjectProvider<Station> stationBuilding1Provider;

    @Autowired
    @Qualifier("stationBuilding2")
    ObjectProvider<Station> stationBuilding2Provider;

    @Autowired
    StationDAORepository stationDAO;

    public Station createStationBuilding1(){
        return stationBuilding1Provider.getObject();
    }

    public Station createStationBuilding2(){
        return stationBuilding2Provider.getObject();
    }

    public void saveStation(Station s){
        stationDAO.save(s);
    }

    public List<Station> getStationsByType(StationType stationType){
        return stationDAO.findByStationType(stationType);
    }
}
