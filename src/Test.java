import DAO.*;

import Controller.VoitureController;
import View.AdminMain;
import View.LoginTypeForm;

import javax.swing.*;
import java.sql.SQLException;
import Controller.Controller;
import DAO.*;
public class Test {

    public static void main(String[] args) {
        try {
            VoitureDAOImpl voitureDAO = new VoitureDAOImpl();
            ClientDAOImpl clientDAO = new ClientDAOImpl();
            ReservationDAOImpl reservationDAO = new ReservationDAOImpl();

            // Assurez-vous de connecter vos DAO ici
            voitureDAO.connect("jdbc:mysql://localhost:3306/projet-locationdevoiture", "root", "");
            clientDAO.connect("jdbc:mysql://localhost:3306/projet-locationdevoiture", "root", "");
            reservationDAO.connect("jdbc:mysql://localhost:3306/projet-locationdevoiture", "root", "");

            Controller controller = new Controller(voitureDAO, clientDAO, reservationDAO);

            // Remplacer AdminMain par LoginForm
            boolean isAdmin = false; // Mettez ceci à true si vous voulez lancer directement la connexion admin
            new LoginTypeForm(clientDAO,controller).setVisible(true); // LoginForm attend un boolean pour savoir s'il s'agit d'une connexion admin ou non
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur de connexion à la base de données.");
        }
    }
}
