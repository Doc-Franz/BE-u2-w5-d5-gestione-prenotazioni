package com.example.gestionePrenotazioni.repository;

import com.example.gestionePrenotazioni.enumeration.StationType;
import com.example.gestionePrenotazioni.model.Station;
import com.example.gestionePrenotazioni.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.naming.Name;
import java.util.List;

@Repository

public interface StationDAORepository extends JpaRepository<Station, Long> {
    List<Station> findByStationType(StationType stationType);
    List<Station> findStationByCity(String city);
    Station findStationById(long userId);
}
