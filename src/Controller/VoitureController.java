package Controller;

import Model.Voiture;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
public class VoitureController {
    private List<Voiture> voitures;

    public VoitureController() {
        this.voitures = new ArrayList<>();
    }

    // Méthode pour ajouter une voiture à la liste
    public void ajouterVoiture(Voiture voiture) {
        voitures.add(voiture);
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

    @Override
    public String toString() {
        return "VoitureController{" +
                "voitures=" + voitures +
                '}';
    }
}


