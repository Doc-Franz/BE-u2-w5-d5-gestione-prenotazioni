package com.example.gestionePrenotazioni.runner;

import com.example.gestionePrenotazioni.enumeration.StationType;
import com.example.gestionePrenotazioni.model.Building;

import com.example.gestionePrenotazioni.model.Reservation;
import com.example.gestionePrenotazioni.model.Station;
import com.example.gestionePrenotazioni.model.User;
import com.example.gestionePrenotazioni.service.BuildingService;
import com.example.gestionePrenotazioni.service.ReservationService;
import com.example.gestionePrenotazioni.service.StationService;
import com.example.gestionePrenotazioni.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class ReservationRunner implements CommandLineRunner {

    public static Scanner sc = new Scanner(System.in);

    @Autowired
    BuildingService buildingService;

    @Autowired
    UserService userService;

    @Autowired
    StationService stationService;

    @Autowired
    ReservationService reservationService;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Running...");

        // ❗❗Prima vanno fatti andare i primi 3 metodi per riempire il database e bisogna commentare makeReservation()
        generateBuilding();
        generateUser();
        generateStation();

        // ❗❗ Dopo aver generato il database commentare i primi 3 metodi ed attivare solamente questo metodo
        makeReservation();

    }

    // metodo per la generazione dei building e il salvataggio nel db
    public void generateBuilding(){
        Building b1 = buildingService.createBuilding1();
        buildingService.saveBuilding(b1);
        Building b2 = buildingService.createBuilding2();
        buildingService.saveBuilding(b2);
    }

    // metodo per la generazione di user e il salvataggio nel db
    public void generateUser(){
        User userAdmin = userService.createUserAdmin();
        userService.saveUser(userAdmin);

        User u1 = userService.createUser();
        userService.saveUser(u1);
    }

    // metodo per la generazione di station e il salvataggio nel db
    public void generateStation(){
        for (int i = 0; i < 5; i++){
            stationService.saveStation(stationService.createStationBuilding1());
            stationService.saveStation(stationService.createStationBuilding2());
        }
    }

    // metodo per la creazione di una nuova prenotazione e salvataggio nel db
    public void generateReservation(LocalDate reservationDate, User user, Station station){

        // verificare se esiste già una prenotazione effettuata dall'utente in quella data
        List<Reservation> reservationListByDate = reservationService.getReservationsByUserAndDate(user, reservationDate);
        List<Reservation> reservationListByStation = reservationService.getReservationsByStationAndDate(station, reservationDate);
        if (reservationListByDate.isEmpty() && reservationListByStation.isEmpty()){
            reservationService.saveReservation(reservationService.createReservation(reservationDate, user, station));
            System.out.println("Prenotazione delle postazione effettuato correttamente!");
        } else {
            System.out.println("Esiste già una prenotazione effettuata per questa data");
        }
    }

    public void makeReservation(){
        System.out.println("Questo è l'elenco di tutte le postazioni");
        List<Station> stationList = stationService.getAllStations();
        stationList.forEach(System.out::println);
        System.out.println("Di che tipo di postazione hai bisogno?");
        System.out.println("-1- postazione privata");
        System.out.println("-2- openspace");
        System.out.println("-3- conference room");
        chooseStation();
    }

    public void chooseStation(){
        boolean notValidValue = true;
        while (notValidValue) {
            String stationType = sc.nextLine();
            switch (stationType) {
                case "1": {
                    List<Station> privateStations = stationService.getStationsByType(StationType.PRIVATE);
                    System.out.println("Elenco delle postazioni private:");
                    privateStations.forEach(System.out::println);
                    notValidValue = false;
                    chooseCity(privateStations);
                    break;
                }
                case "2": {
                    List<Station> openSpaceStations = stationService.getStationsByType(StationType.OPENSPACE);
                    System.out.println("Elenco delle postazioni openspace:");
                    openSpaceStations.forEach(System.out::println);
                    notValidValue = false;
                    chooseCity(openSpaceStations);
                    break;
                }
                case "3": {
                    List<Station> conferenceRoomStations = stationService.getStationsByType(StationType.CONFERENCE_ROOM);
                    System.out.println("Elenco delle postazioni conference room:");
                    conferenceRoomStations.forEach(System.out::println);
                    notValidValue = false;
                    chooseCity(conferenceRoomStations);
                    break;
                }
                default:
                    System.out.println("Inserire un valore valido");
            }
        }
    }

    // metodo che gestisce la scelta della città
    public void chooseCity(List<Station> stationList){
        System.out.println("");
        System.out.println("In che città intendi prenotare questo tipo di postazione?");
        String city = sc.nextLine();
        List<Station> cityStations = stationList.stream()
                .filter(station -> station.getCity().equalsIgnoreCase(city))
                .toList();
        System.out.println("Questo è l'elenco delle postazioni nella città " + city);
        cityStations.forEach(System.out::println);
        if (cityStations.isEmpty()) {
            System.out.println("Nessuna postazione disponibile nella città: " + city);
            return;
        }
        chooseUserAndStation();
    }

    // metodo che gestisce la scelta della postazione tramite interfaccia
    public void chooseUserAndStation(){
        boolean wantToContinue = true;
        while (wantToContinue){
            // selezione della postazione
            System.out.println("");
            System.out.println("Digita l'id della postazione che intendi prenotare:");
            long stationId = Long.parseLong(sc.nextLine());
            Station station = stationService.getStationById(stationId);
            System.out.println("La postazione selezionata è " + station);

            System.out.println("");
            System.out.println("Questo è l'elenco degli utenti che possono prenotare la postazione:");
            userService.showUserList();

            // selezione dell'utente
            System.out.println("Digita l'id del'utente che intende prenotare la postazione");
            long userId = Long.parseLong(sc.nextLine());
            User user = userService.getUserById(userId);
            System.out.println("L'utente selezionato è " + user);

            // selezione della data
            System.out.println("Digita la data (yyyy-mm-dd) in cui intendi prenotare la postazione: ");
            LocalDate reservationDate = LocalDate.parse(sc.nextLine());

            generateReservation(reservationDate, user, station);

            // booleano che controlla se l'utente digita correttamente 'y' o 'n'
            boolean userAnswer = true;
            while (userAnswer)
            {
                System.out.println("Vuoi prenotare un'altra postazione? 'y' per continuare oppure 'n' per uscire");
                String userChoice = sc.nextLine();
                if (userChoice.equals("n")) {
                    wantToContinue = false;
                    userAnswer = false;
                    System.out.println("Chiusura dell'applicazione per prenotare le postazioni...");
                } else if (userChoice.equals("y")) {
                    userAnswer = false;
                } else {
                    System.out.println("Inserisci una risposta corretta");
                }
            }

        }
    }

}
