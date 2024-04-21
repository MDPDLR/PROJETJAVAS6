package Controller;

import DAO.ReservationDAO;
import Model.Client;
import Model.Reservation;
import Model.Voiture;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


import java.util.ArrayList;
import java.util.List;

public class ReservationController {
    private ReservationDAO reservationDAO;
    private List<Reservation> reservations;


    public ReservationController(ReservationDAO reservationDAO) {
        this.reservationDAO = reservationDAO;
        this.reservations = reservationDAO.getAllReservations();
    }


    // Méthode pour supprimer une réservation
    public void supprimerReservation(Reservation reservation) {
        reservationDAO.supprimerReservation(reservation);
    }

    // Méthode pour afficher toutes les réservations
    public void afficherReservations() {
        List<Reservation> reservations = reservationDAO.getAllReservations();
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation disponible.");
        } else {
            System.out.println("Liste des réservations :");
            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
    }

    // Méthode pour afficher les réservations d'un client spécifique
    public void afficherReservationsClient(String email) {
        List<Reservation> reservations = reservationDAO.getReservationsForClient(email);
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation pour le client " + email + ".");
        } else {
            System.out.println("Réservations du client " + email + " :");
            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
    }
    public List<Reservation> getReservationsForClient(String email) {
        return reservationDAO.getReservationsForClient(email);
    }

    public ReservationDAO getReservationDAO() {
        return reservationDAO;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    @Override
    public String toString() {
        return "ReservationController{" +
                ", reservations=" + reservations +
                '}';
    }
}
