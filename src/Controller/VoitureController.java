package Controller;

import Model.Voiture;
import DAO.VoitureDAO;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class VoitureController {
    private List<Voiture> voitures;
    private VoitureDAO voitureDAO;

    public VoitureController(VoitureDAO voitureDAO) {
        this.voitureDAO = voitureDAO;
        // Charger les voitures depuis la base d1e données lors de la création du contrôleur
        this.voitures = voitureDAO.getAllVoitures();
    }

    // Méthode pour ajouter une voiture à la liste et à la base de données
    public void ajouterVoiture(Voiture voiture) {
        voitures.add(voiture);
        // Ajouter la voiture à la base de données
        voitureDAO.ajouterVoiture(voiture);
        System.out.println("Voiture ajoutée avec succès : " + voiture);
    }

    // Méthode pour trier et afficher les voitures par type
    public void trierEtAfficherParType() {
        if (voitures.isEmpty()) {
            System.out.println("Aucune voiture disponible.");
            return;
        }

        // Trier la liste par type de voiture
        Collections.sort(voitures, Comparator.comparing(Voiture::getType));

        // Afficher les voitures triées par type
        System.out.println("Liste des voitures triées par type :");
        for (Voiture voiture : voitures) {
            System.out.println(voiture.toString());
        }
    }

    public void modifierDisponibiliteVoiture(String id, boolean disponible) {
        // Modifier la disponibilité de la voiture dans la base de données
        voitureDAO.modifierDisponibiliteVoiture(id, disponible);

        // Modifier la disponibilité de la voiture dans la liste de voitures du contrôleur
        for (Voiture voiture : voitures) {
            if (voiture.getVoitureID().equals(id)) {
                voiture.setDisponibilite(disponible);
                System.out.println("La disponibilité de la voiture a été modifiée avec succès dans le contrôleur.");
                break;
            }
        }


    }


    @Override
    public String toString() {
        return "VoitureController{" +
                "voitures=" + voitures +
                '}';
    }
}
