package Controller;

import Model.Reservation;
import java.util.ArrayList;
import java.util.List;

public class ReservationController {
    private List<Reservation> reservations;

    public ReservationController() {
        this.reservations = new ArrayList<>();
    }

    // Méthode pour ajouter une réservation à la liste
    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
        System.out.println("Réservation ajoutée avec succès : " + reservation);
    }

    // Méthode pour supprimer une réservation de la liste
    public void supprimerReservation(Reservation reservation) {
        if (reservations.remove(reservation)) {
            System.out.println("Réservation supprimée avec succès : " + reservation);
        } else {
            System.out.println("La réservation n'a pas été trouvée dans la liste.");
        }
    }

    // Méthode pour afficher toutes les réservations de la liste
    public void afficherReservations() {
        if (reservations.isEmpty()) {
            System.out.println("Aucune réservation disponible.");
        } else {
            System.out.println("Liste des réservations :");
            for (Reservation reservation : reservations) {
                System.out.println(reservation.toString());
            }
        }
    }

    // Autres méthodes pour gérer les opérations sur les réservations
}
