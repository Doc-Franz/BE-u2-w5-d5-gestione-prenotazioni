package com.example.gestionePrenotazioni.service;

import com.example.gestionePrenotazioni.enumeration.StationType;
import com.example.gestionePrenotazioni.model.Building;
import com.example.gestionePrenotazioni.model.Station;
import com.example.gestionePrenotazioni.model.User;
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

    // ricerca di tutte le postazioni
    public List<Station> getAllStations(){
        return stationDAO.findAll();
    }

    // ricerca di una postazione tramite il tipo
    public List<Station> getStationsByType(StationType stationType){
        return stationDAO.findByStationType(stationType);
    }

    // ricerca di una postazione tramite la città
    public List<Station> getStationByCity(String city){
        return stationDAO.findStationByCity(city);
    }

    // ricerca di una postazione tramite id
    public Station getStationById(long stationId){
        return stationDAO.findStationById(stationId);
    }
}
