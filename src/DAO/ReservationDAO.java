package DAO;

import Model.Reservation;
import Model.Voiture;

import java.sql.SQLException;
import java.util.List;

public interface ReservationDAO {
    // Méthode pour ajouter une réservation
    public void connect(String URLDataBase, String LoginDataBase, String PwdDataBase) throws SQLException, ClassNotFoundException;
    void ajouterReservation(Reservation reservation);

    // Méthode pour supprimer une réservation
    void supprimerReservation(Reservation reservation);

    // Méthode pour obtenir toutes les réservations
    List<Reservation> getAllReservations();

    // Méthode pour obtenir les réservations d'un client spécifique
    List<Reservation> getReservationsForClient(String email);
    public Voiture getVoitureById(String id);

    }
