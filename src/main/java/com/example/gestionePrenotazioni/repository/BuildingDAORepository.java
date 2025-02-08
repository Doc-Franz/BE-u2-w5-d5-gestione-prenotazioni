package com.example.gestionePrenotazioni.repository;

import com.example.gestionePrenotazioni.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface BuildingDAORepository extends JpaRepository<Building, Long> {}
