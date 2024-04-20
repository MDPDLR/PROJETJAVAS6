package View;
import DAO.ClientDAOImpl;
import View.LoginForm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import DAO.ClientDAOImpl;
import javax.swing.*;
import java.awt.*;
import Controller.*;
public class LoginTypeForm extends JFrame {
    private ClientDAOImpl clientDAO;
    private Controller controller;

    public LoginTypeForm(ClientDAOImpl clientDAO, Controller controller) {
        this.clientDAO = clientDAO;
        this.controller= controller;
        setTitle("Type de Connexion");
        setSize(300, 100);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JButton userButton = new JButton("Connexion Utilisateur");
        JButton adminButton = new JButton("Connexion Admin");

        userButton.addActionListener(e -> openLoginForm(false));
        adminButton.addActionListener(e -> openLoginForm(true));

        add(userButton);
        add(adminButton);

        setVisible(true);
    }
    private void openMainMenu() {
        MainMenu mainMenu = new MainMenu(controller,clientDAO);
        mainMenu.setVisible(true);
        this.dispose(); // Ferme LoginTypeForm
    }
    private void openLoginForm(boolean isAdmin) {
        this.dispose();  // Fermer cette fenêtre
        new LoginForm(clientDAO, isAdmin,controller);  // Ouvrir le formulaire de connexion avec ClientDAOImpl et isAdmin
    }


}

