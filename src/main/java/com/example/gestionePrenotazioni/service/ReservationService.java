package com.example.gestionePrenotazioni.service;

import com.example.gestionePrenotazioni.model.Reservation;
import com.example.gestionePrenotazioni.model.Station;
import com.example.gestionePrenotazioni.model.User;
import com.example.gestionePrenotazioni.repository.ReservationDAORepository;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service

public class ReservationService {

    @Autowired
    ReservationDAORepository reservationDAO;

    public Reservation createReservation(LocalDate reservationDate, User user, Station station){
        return new Reservation(reservationDate, user, station);
    }

    public void saveReservation(Reservation r){
        reservationDAO.save(r);
    }

    public List<Reservation> getReservationsByUserAndDate(User user, LocalDate reservationDate){
        return reservationDAO.findByUserAndReservationDate(user, reservationDate);
    }
}
