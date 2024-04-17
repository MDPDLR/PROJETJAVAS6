import Controller.ClientController;
import Controller.ReservationController;
import Controller.VoitureController;
import DAO.*;
import Model.Client;
import Model.Reservation;
import Model.Voiture;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        try {
            // Crée une instance de VoitureDAOImpl et connecte-toi à la base de données
            VoitureDAOImpl voitureDAOImpl = new VoitureDAOImpl();
            voitureDAOImpl.connect("jdbc:mysql://localhost:8889/Projet-LocationDeVoiture", "root", "root");
            // Crée une instance de VoitureController en lui passant VoitureDAOImpl
            VoitureController voitureController = new VoitureController(voitureDAOImpl);
            Client client = new Client("martindeperetti@gmail.com","dssds","dsdsd","dsdsds",2,true);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");

            LocalDate dateDebut = LocalDate.parse("01/01/01", formatter);
            LocalDate dateFin = LocalDate.parse("03/01/01", formatter);

            Reservation resa = new Reservation(2121,dateDebut,dateFin,voitureDAOImpl.getVoitureById("123243"),client);
            ReservationDAOImpl reservationDAOImpl = new ReservationDAOImpl();
            reservationDAOImpl.connect("jdbc:mysql://localhost:8889/Projet-LocationDeVoiture", "root", "root");
            ReservationController reservationController = new ReservationController(reservationDAOImpl);
            reservationController.ajouterReservation(resa);

            System.out.println(reservationController.toString());
            // Affiche la liste des voitures
            //System.out.println(voitureController.toString());
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
