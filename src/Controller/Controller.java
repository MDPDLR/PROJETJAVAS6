package Controller;

import DAO.*;
import Model.*;


import java.time.LocalDate;

public class Controller {

    public VoitureController voitureController;
    public ClientController clientController;
    public ReservationController reservationController;

    public Controller(VoitureDAOImpl voitureDAOImpl, ClientDAOImpl clientDAOImpl, ReservationDAOImpl reservationDAOImpl) {
        this.voitureController = new VoitureController(voitureDAOImpl);
        this.clientController = new ClientController(clientDAOImpl);
        this.reservationController = new ReservationController(reservationDAOImpl);
    }

    public VoitureController getVoitureController() {
        return voitureController;
    }

    public ClientController getClientController() {
        return clientController;
    }

    public ReservationController getReservationController() {
        return reservationController;
    }

    public void ajouterReservation(LocalDate dateDebut, LocalDate dateFin, Voiture voiture, Client client) {
        if (voitureController.getVoitureById(voiture.getVoitureID()) == null) {
            System.out.println("La voiture spécifiée n'existe pas.");
            return;
        }

        // Vérifier si le client existe
        if (clientController.getClientByMail(client.getMail()) == null) {
            System.out.println("Le client spécifié n'existe pas.");
            return;
        }




        // Créer la réservation avec les informations fournies
        Reservation nouvelleReservation = new Reservation(dateDebut, dateFin, voiture, client);

        // Ajouter la réservation à la liste de réservations et à la base de données
        reservationController.getReservationDAO().ajouterReservation(nouvelleReservation);
        reservationController.getReservations().add(nouvelleReservation);

        // Afficher un message pour indiquer que la réservation a été ajoutée avec succès
        System.out.println("Réservation ajoutée avec succès : " + nouvelleReservation);


        // Modifier la disponibilité du véhicule en le réservant
        voitureController.modifierDisponibiliteVoiture(voiture.getVoitureID(), false);

        // Ajouter la réservation à la liste des réservations du client
        clientController.ajouterReservation(client, nouvelleReservation);
    }

    public void updateDisponibiliteVoitures() {
        LocalDate currentDate = LocalDate.now();

        for (Reservation reservation : reservationController.getReservations()) {
            Voiture voiture = reservation.getVoiture();
            LocalDate dateDebut = reservation.getDateDebut();
            LocalDate dateFin = reservation.getDateFin();

            if (currentDate.isAfter(dateDebut) && currentDate.isBefore(dateFin)) {
                // donc la voiture est indisponible
                voitureController.modifierDisponibiliteVoiture(voiture.getVoitureID(), false);
            } else {
                // La date actuelle est en dehors de la période de réservation, donc la voiture est disponible
                voitureController.modifierDisponibiliteVoiture(voiture.getVoitureID(), true);
            }
        }

        System.out.println("Mise à jour de la disponibilité des voitures terminée.");
    }

    @Override
    public String toString() {
        return "Controller{" +
                "\nvoitureController=" + voitureController +
                ", \nclientController=" + clientController +
                ", \nreservationController=" + reservationController +
                '}';
    }
}
