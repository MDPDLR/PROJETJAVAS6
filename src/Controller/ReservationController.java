package Controller;

import Model.Reservation;
import Model.Voiture;
import Model.Client;
import java.util.Date;

public class ReservationController {
    public void afficherDetailsReservation(Reservation reservation) {
        System.out.println("Détails de la réservation:");
        System.out.println("Book ID: " + reservation.getBookID());
        System.out.println("Date de début: " + reservation.getDateDebut());
        System.out.println("Date de fin: " + reservation.getDateFin());
        System.out.println("Montant: " + reservation.getMontant() + " $");
        if (reservation.getVoiture() != null) {
            System.out.println("Voiture louée: " + reservation.getVoiture().getModele());
        } else {
            System.out.println("Aucune voiture associée à cette réservation.");
        }
    }
}