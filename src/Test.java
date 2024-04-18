import Controller.Controller;
import DAO.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.sql.SQLException;
import java.time.LocalDate;

public class Test {
    public static void main(String[] args) {
        try {
            VoitureDAOImpl voitureDAOImpl = new VoitureDAOImpl();
            voitureDAOImpl.connect("jdbc:mysql://localhost:8889/Projet-LocationDeVoiture", "root", "root");

            ReservationDAOImpl reservationDAOImpl = new ReservationDAOImpl();
            reservationDAOImpl.connect("jdbc:mysql://localhost:8889/Projet-LocationDeVoiture", "root", "root");

            ClientDAOImpl clientDAOImpl = new ClientDAOImpl();
            clientDAOImpl.connect("jdbc:mysql://localhost:8889/Projet-LocationDeVoiture", "root", "root");

            Controller controller = new Controller(voitureDAOImpl,clientDAOImpl,reservationDAOImpl);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yy");
            LocalDate dateDebut = LocalDate.parse("01/01/01", formatter);
            LocalDate dateFin = LocalDate.parse("03/01/01", formatter);

            controller.voitureController.ajouterVoiture("sfsdrsf","dfsdfdsf","dsfdsf",1,23432);
            controller.clientController.ajouterClient("dasdsafsd","dasdsad","Martin","de Peretti",1,true);

            System.out.println(controller.voitureController.getVoitureById("sfsdrsf"));
            System.out.println(controller.clientController.getClientByMail("dasdsafsd"));

            controller.ajouterReservation(dateDebut,dateFin,controller.voitureController.getVoitureById("sfsdrsf"),controller.clientController.getClientByMail("dasdsafsd"));

            System.out.println(controller.toString());
            // Affiche la liste des voitures
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
