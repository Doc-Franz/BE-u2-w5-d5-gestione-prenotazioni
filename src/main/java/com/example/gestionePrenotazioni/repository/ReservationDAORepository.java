package com.example.gestionePrenotazioni.repository;

import com.example.gestionePrenotazioni.model.Reservation;
import com.example.gestionePrenotazioni.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface ReservationDAORepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByUserAndReservationDate(User user, LocalDate reservationDate);
}
