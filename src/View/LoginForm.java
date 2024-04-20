
package View;

import javax.swing.*;
import Controller.Controller;
import Controller.VoitureController;
import DAO.ClientDAOImpl;
import DAO.ReservationDAOImpl;
import DAO.VoitureDAOImpl;
import Model.Client;

import java.awt.*;

public class LoginForm extends JFrame {
    private JTextField userIdField;
    private JPasswordField passwordField;
    private boolean isAdmin;
    private ClientDAOImpl clientDAO;
    private Controller controller;

    public LoginForm(ClientDAOImpl clientDAO, boolean isAdmin,Controller controller) {
        this.controller = controller;
        this.clientDAO = clientDAO;
        this.isAdmin = isAdmin;
        initializeUI();
    }

    private void initializeUI() {
        String title = isAdmin ? "Connexion Admin" : "Connexion Utilisateur";
        setTitle(title);
        setLayout(new GridLayout(3, 2));
        add(new JLabel("Email (ou ID pour admin):"));
        userIdField = new JTextField(20);
        add(userIdField);
        add(new JLabel("Mot de passe:"));
        passwordField = new JPasswordField(20);
        add(passwordField);
        JButton loginButton = new JButton("Connexion");
        loginButton.addActionListener(e -> validateLogin());
        add(loginButton);

        setSize(300, 120);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void validateLogin() {
        String emailOrId = userIdField.getText();
        String password = new String(passwordField.getPassword());

        if (isAdmin) {
            // Supposons que `validateAdmin` vérifie correctement les identifiants admin
            if (clientDAO.validateAdmin(emailOrId, password)) {
                JOptionPane.showMessageDialog(this, "Connexion admin réussie.");
                openAdminMain(); // Ouverture de la fenêtre d'administration avec le bon contrôleur
            } else {
                JOptionPane.showMessageDialog(this, "Identifiants admin incorrects.");
            }
        } else {
            Client client = clientDAO.getClientByEmail(emailOrId);
            if (client != null && client.getMdp().equals(password)) {
                JOptionPane.showMessageDialog(this, "Bienvenue " + client.getPrenom() + " " + client.getNom());
                SessionManager.getInstance().setCurrentUser(client);
                openMainMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Email ou mot de passe incorrect.");
            }
        }
    }

    private void openMainMenu() {
        // Assuming controller is a class-level attribute initialized at some point.
        new MainMenu(controller,clientDAO).setVisible(true);
        this.dispose(); // Close the current login form
    }

    private void openAdminMain() {
        try {
            // Initialiser VoitureDAOImpl et connecter à la base de données
            ClientDAOImpl clientDAO = new ClientDAOImpl();
            VoitureDAOImpl voitureDAO = new VoitureDAOImpl();
            ReservationDAOImpl reservationDAO = new ReservationDAOImpl();

            // Connecter à la base de données
            clientDAO.connect("jdbc:mysql://localhost:3306/projet-locationdevoiture", "root", "");
            voitureDAO.connect("jdbc:mysql://localhost:3306/projet-locationdevoiture", "root", "");
            reservationDAO.connect("jdbc:mysql://localhost:3306/projet-locationdevoiture", "root", "");

            // Créer le Controller avec tous les DAO
            Controller controller = new Controller(voitureDAO, clientDAO, reservationDAO);
            // Ouvrir la fenêtre d'administration avec le contrôleur
            dispose(); // Fermer la fenêtre de connexion
            new AdminMain(controller).setVisible(true); // Ouvrir la fenêtre d'administration
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur lors de la connexion à la base de données ou lors de l'initialisation du contrôleur.");
        }
    }

}
