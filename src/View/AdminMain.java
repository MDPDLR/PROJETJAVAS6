package View;

import Controller.Controller;
import Controller.VoitureController;
import DAO.ClientDAOImpl;
import Model.Voiture;
import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;


public class AdminMain extends JFrame {
    private VoitureController voitureController;
    private JList<Voiture> listVoitures;
    private DefaultListModel<Voiture> listModel;
    private ClientDAOImpl clientDAO;
    private Controller controller;

    public AdminMain(Controller controller) {
        this.controller = controller;
        this.voitureController = controller.getVoitureController(); // Correctement initialiser voitureController via le Controller passé

        // Étant donné que clientDAO est utilisé ici, assurez-vous qu'il est également correctement initialisé avant cet appel.
        try {
            clientDAO = new ClientDAOImpl();
            clientDAO.connect("jdbc:mysql://localhost:3306/projet-locationdevoiture", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Erreur de connexion à la base de données.");
        }

        setTitle("Gestion des Voitures");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setupUI();
        setVisible(true);
    }


    private void setupUI() {
        JPanel panel = new JPanel(new BorderLayout());
        listModel = new DefaultListModel<>();
        updateVoitureList();

        listVoitures = new JList<>(listModel);
        listVoitures.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(listVoitures);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel southPanel = new JPanel();
        JButton btnAdd = new JButton("Ajouter Voiture");
        JButton btnRemove = new JButton("Retirer Voiture");
        JButton btnLogout = new JButton("Déconnexion");

        btnAdd.addActionListener(e -> addVoiture());
        btnRemove.addActionListener(e -> removeSelectedVoiture());
        btnLogout.addActionListener(e -> logout());

        southPanel.add(btnAdd);
        southPanel.add(btnRemove);
        southPanel.add(btnLogout);

        panel.add(southPanel, BorderLayout.SOUTH);

        getContentPane().add(panel);
    }

    private void updateVoitureList() {
        listModel.clear();
        for (Voiture voiture : voitureController.getVoitures()) {
            listModel.addElement(voiture);
        }
    }

    private void addVoiture() {
        // Créez des champs de texte pour saisir les informations de la nouvelle voiture
        JTextField voitureIDField = new JTextField();
        JTextField modeleField = new JTextField();
        JTextField marqueField = new JTextField();
        JTextField typeField = new JTextField();
        JTextField prixField = new JTextField();
        JTextField disponibiliteField = new JTextField("true");  // Pré-rempli avec "true"
        JTextField photoField = new JTextField();

        // Créez un tableau d'Object pour le message du JOptionPane
        Object[] message = {
                "Voiture ID:", voitureIDField,
                "Marque:", marqueField,
                "Modèle:", modeleField,
                "Type (int):", typeField,
                "Prix (float):", prixField,
                "Disponibilité (boolean):", disponibiliteField,
                "Photo (URL):", photoField
        };

        // Affichez un JOptionPane pour saisir les informations
        int option = JOptionPane.showConfirmDialog(null, message, "Ajouter une nouvelle voiture", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                // Créez une nouvelle instance de Voiture avec les valeurs saisies
                Voiture nouvelleVoiture = new Voiture(
                        voitureIDField.getText(),
                        marqueField.getText(),
                        modeleField.getText(),
                        Integer.parseInt(typeField.getText()),
                        Float.parseFloat(prixField.getText()),
                        Boolean.parseBoolean(disponibiliteField.getText()),
                        photoField.getText()
                );

                // Utilisez le VoitureController pour ajouter la voiture
                voitureController.ajouterVoiture(
                        nouvelleVoiture.getVoitureID(),
                        nouvelleVoiture.getModele(),
                        nouvelleVoiture.getMarque(),
                        nouvelleVoiture.getType(),
                        nouvelleVoiture.getPrix(),
                        nouvelleVoiture.isDisponible(),
                        nouvelleVoiture.getPhoto()
                );

                // Mettez à jour la liste des voitures affichées
                updateVoitureList();
                JOptionPane.showMessageDialog(this, "Voiture ajoutée avec succès : " + nouvelleVoiture);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Erreur dans les formats des numéros entrés (type ou prix).");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erreur lors de l'ajout de la voiture. " + e.getMessage());
            }
        }
    }


    private void removeSelectedVoiture() {
        Voiture selected = listVoitures.getSelectedValue();
        if (selected != null) {
            try {
                if (voitureController.voitureHasReservations(selected.getVoitureID())) {
                    JOptionPane.showMessageDialog(this,
                            "Impossible de supprimer la voiture car elle a des réservations actives.",
                            "Erreur de suppression",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    voitureController.supprimerVoiture(selected.getVoitureID());
                    updateVoitureList();  // Mettre à jour la liste seulement si la suppression a réussi
                    JOptionPane.showMessageDialog(this,
                            "Voiture supprimée avec succès.",
                            "Suppression réussie",
                            JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this,
                        "Erreur lors de la suppression de la voiture: " + e.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void logout() {
        SessionManager.getInstance().setCurrentUser(null);
        this.dispose(); // Fermer MainMenu
        new LoginTypeForm(clientDAO, controller).setVisible(true); // Réutiliser les instances existantes
    }


}
