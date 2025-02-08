package com.example.gestionePrenotazioni.repository;

import com.example.gestionePrenotazioni.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserDAORepository extends JpaRepository<User, Long> {}
