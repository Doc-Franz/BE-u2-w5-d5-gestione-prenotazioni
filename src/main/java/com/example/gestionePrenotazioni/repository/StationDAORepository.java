package com.example.gestionePrenotazioni.repository;

import com.example.gestionePrenotazioni.enumeration.StationType;
import com.example.gestionePrenotazioni.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface StationDAORepository extends JpaRepository<Station, Long> {
    List<Station> findByStationType(StationType stationType);
}
