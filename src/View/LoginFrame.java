package View;

import javax.swing.*;
import Controller.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrame extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private Controller controller;

    public LoginFrame(Controller controller) {
        this.controller = controller;
        setTitle("Connexion");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        emailField = new JTextField();
        emailField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, emailField.getPreferredSize().height));
        add(emailField);

        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new java.awt.Dimension(Integer.MAX_VALUE, passwordField.getPreferredSize().height));
        add(passwordField);

        loginButton = new JButton("Se connecter");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                if (controller.getClientController().getClientByMail(email) != null && controller.getClientController().getClientByMail(email).getMdp().equals(password)) {
                    new MainFrame(controller).setVisible(true);
                    dispose();  // Ferme la fenêtre de connexion
                } else {
                    JOptionPane.showMessageDialog(null, "Identifiants incorrects");
                }
            }
        });
        add(loginButton);

        setLocationRelativeTo(null);
    }
}
