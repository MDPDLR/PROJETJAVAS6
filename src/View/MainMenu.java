package View;

import DAO.ClientDAOImpl;
import Model.Client;
import Model.Voiture;
import DAO.VoitureDAOImpl;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import Controller.Controller;
import java.util.List;
public class MainMenu extends JFrame {
    private CardLayout cardLayout = new CardLayout();
    private JPanel cardPanel = new JPanel(cardLayout);
    private Client currentUser;
    private DefaultListModel<Voiture> model = new DefaultListModel<>(); // Déplacez ici

    private Controller controller;
    // Couleurs du thème
    private final Color backgroundColor = new Color(30, 30, 30); // Un gris très foncé
    private final Color foregroundColor = new Color(180, 70, 250); // Un violet
    private ClientDAOImpl clientDAO;

    public MainMenu(Controller controller, ClientDAOImpl clientDAO) {
        this.controller = controller;
        this.clientDAO = clientDAO;  // Save the passed clientDAO
        currentUser = SessionManager.getInstance().getCurrentUser();
        if (currentUser == null) {
            JOptionPane.showMessageDialog(this, "Erreur: Aucune information d'utilisateur disponible.");
            System.exit(1);
        }

        setTitle("Menu Principal");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(backgroundColor);

        setupMenuPanel();
        setupCardPanel();
        setupAccountPanel();

        add(cardPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private void setupMenuPanel() {
        JPanel menuPanel = new JPanel(new GridLayout(1, 5));  // Augmentez à 5 pour ajouter le bouton Accueil
        menuPanel.setBackground(backgroundColor); // Appliquer la couleur de fond

        JButton btnHome = createStyledButton("Accueil");
        JButton btnReservations = createStyledButton("Mes Réservations");
        JButton btnLocation = createStyledButton("Location");
        JButton btnContact = createStyledButton("Contact");
        JButton btnAccount = createStyledButton("Mon Compte");

        btnHome.addActionListener(e -> cardLayout.show(cardPanel, "Home"));
        btnReservations.addActionListener(e -> cardLayout.show(cardPanel, "Reservations"));
        btnLocation.addActionListener(e -> cardLayout.show(cardPanel, "Location"));
        btnContact.addActionListener(e -> cardLayout.show(cardPanel, "Contact"));
        btnAccount.addActionListener(e -> cardLayout.show(cardPanel, "Account"));

        menuPanel.add(btnHome);
        menuPanel.add(btnReservations);
        menuPanel.add(btnLocation);
        menuPanel.add(btnContact);
        menuPanel.add(btnAccount);

        add(menuPanel, BorderLayout.NORTH);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setForeground(Color.WHITE); // Texte blanc
        button.setBackground(foregroundColor); // Fond violet
        return button;
    }

    private void setupCardPanel() {
        JPanel homePanel = new JPanel(new GridLayout(2, 2));
        homePanel.setBackground(backgroundColor);
        homePanel.add(new JLabel("Bienvenue à l'accueil!", JLabel.CENTER));

        setupLocationPanel();  // Configurer le panneau de location

        JPanel contactPanel = new JPanel();
        contactPanel.setBackground(backgroundColor);
        contactPanel.add(new JLabel("Contenu Contact ici", JLabel.CENTER));

        JPanel reservationsPanel = new JPanel();
        reservationsPanel.setBackground(backgroundColor);
        reservationsPanel.add(new JLabel("Contenu Réservations ici", JLabel.CENTER));

        JPanel accountPanel = setupAccountPanel();

        cardPanel.add(homePanel, "Home");
        cardPanel.add(contactPanel, "Contact");
        cardPanel.add(reservationsPanel, "Reservations");
        cardPanel.add(accountPanel, "Account");

        cardLayout.show(cardPanel, "Home");  // Afficher la page d'accueil par défaut
    }

    private JPanel setupAccountPanel() {
        JPanel accountPanel = new JPanel(new GridLayout(6, 2));
        accountPanel.setBackground(backgroundColor); // Appliquer la couleur de fond

        addLabelAndValue(accountPanel, "Email:", currentUser.getMail());
        addLabelAndValue(accountPanel, "Mot de passe:", currentUser.getMdp());
        addLabelAndValue(accountPanel, "Prénom:", currentUser.getPrenom());
        addLabelAndValue(accountPanel, "Nom:", currentUser.getNom());

        JButton btnLogout = createStyledButton("Déconnexion");
        btnLogout.addActionListener(e -> logout());
        accountPanel.add(new JLabel(""));
        accountPanel.add(btnLogout);

        cardPanel.add(accountPanel, "Account");
        return accountPanel;
    }

    private void addLabelAndValue(JPanel panel, String label, String value) {
        JLabel lbl = new JLabel(label);
        lbl.setForeground(Color.WHITE); // Texte blanc
        panel.add(lbl);
        JLabel val = new JLabel(value);
        val.setForeground(Color.LIGHT_GRAY); // Texte gris clair pour les valeurs
        panel.add(val);
    }

    private void logout() {
        SessionManager.getInstance().setCurrentUser(null);
        this.dispose();  // Close the MainMenu

        try {
            new LoginTypeForm(clientDAO, controller).setVisible(true);  // Open LoginTypeForm using existing instances
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erreur lors de la déconnexion.");
        }
    }
    private void setupLocationPanel() {
        JPanel locationPanel = new JPanel(new BorderLayout());
        locationPanel.setBackground(backgroundColor);

        DefaultListModel<Voiture> model = new DefaultListModel<>();
        JList<Voiture> listVoitures = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(listVoitures);
        locationPanel.add(scrollPane, BorderLayout.CENTER);

        updateLocationList(model);  // Assurez-vous que la liste est mise à jour ici

        JPanel bookingPanel = new JPanel();
        JTextField startDateField = new JTextField(10);
        JTextField endDateField = new JTextField(10);
        JButton btnReserve = createStyledButton("Réserver");
        bookingPanel.add(new JLabel("Date de début (yyyy-mm-dd):"));
        bookingPanel.add(startDateField);
        bookingPanel.add(new JLabel("Date de fin (yyyy-mm-dd):"));
        bookingPanel.add(endDateField);
        bookingPanel.add(btnReserve);
        locationPanel.add(bookingPanel, BorderLayout.SOUTH);

        btnReserve.addActionListener(e -> {
            Voiture selectedVoiture = listVoitures.getSelectedValue();
            if (selectedVoiture != null && !startDateField.getText().isEmpty() && !endDateField.getText().isEmpty()) {
                LocalDate startDate = LocalDate.parse(startDateField.getText());
                LocalDate endDate = LocalDate.parse(endDateField.getText());
                if (!startDate.isAfter(endDate)) {
                    // Appel pour réserver le véhicule
                    controller.ajouterReservation(startDate, endDate, selectedVoiture, currentUser);
                    // Rendre le véhicule indisponible
                    controller.getVoitureController().setVehicleUnavailable(selectedVoiture.getVoitureID());
                    // Rafraîchir la liste des véhicules
                    updateLocationList();
                    JOptionPane.showMessageDialog(this, "Réservation réussie !");
                } else {
                    JOptionPane.showMessageDialog(this, "Vérifiez que la date de début est avant la date de fin.", "Erreur de date", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Sélectionnez un véhicule et entrez des dates valides.", "Information manquante", JOptionPane.WARNING_MESSAGE);
            }
        });

        cardPanel.add(locationPanel, "Location");
    }

    private void updateLocationList() {
        model.clear();
        List<Voiture> vehicles = controller.getVoitureController().getVoituresDisponibles();
        for (Voiture voiture : vehicles) {
            if (voiture.isDisponible()) {
                model.addElement(voiture);
            }
        }
    }

    private float calculateAmount(LocalDate start, LocalDate end, float pricePerDay) {
        long days = start.until(end, java.time.temporal.ChronoUnit.DAYS) + 1;  // Inclure les deux jours
        return days * pricePerDay;
    }

    private void showPaymentDialog(float amount, Voiture voiture, LocalDate startDate, LocalDate endDate) {
        JDialog paymentDialog = new JDialog(this, "Paiement", true);
        paymentDialog.setLayout(new GridLayout(5, 2));
        paymentDialog.add(new JLabel("Total à payer:"));
        paymentDialog.add(new JLabel(String.format("%.2f €", amount)));
        paymentDialog.add(new JLabel("Numéro de carte:"));
        JTextField cardField = new JTextField(16);
        paymentDialog.add(cardField);
        paymentDialog.add(new JLabel("Date d'expiration (MM/AA):"));
        JTextField expField = new JTextField(5);
        paymentDialog.add(expField);
        paymentDialog.add(new JLabel("CVV:"));
        JTextField cvvField = new JTextField(3);
        paymentDialog.add(cvvField);

        JButton payButton = new JButton("Payer");
        payButton.addActionListener(e -> {
            paymentDialog.dispose();
            JOptionPane.showMessageDialog(this, "Paiement effectué avec succès pour la voiture " + voiture.getModele() + " du " + startDate + " au " + endDate);
            // Mettre à jour la réservation comme complétée ici
            controller.ajouterReservation(startDate, endDate, voiture, currentUser);
            updateLocationList(model); // Refresh list to show availability
        });
        paymentDialog.add(payButton);

        paymentDialog.setSize(300, 200);
        paymentDialog.setLocationRelativeTo(this);
        paymentDialog.setVisible(true);
    }


    private void updateLocationList(DefaultListModel<Voiture> model) {
        model.clear();
        for (Voiture voiture : controller.getVoitureController().getVoitures()) {
            if (voiture.isDisponible()) {
                model.addElement(voiture);
            }
        }
    }
}
