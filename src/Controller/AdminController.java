package Controller;

import Model.Admin;
import Model.Voiture;
import java.util.ArrayList;
import java.util.List;

public class AdminController {
    private List<Voiture> voitures;

    public AdminController() {
        this.voitures = new ArrayList<>();
    }

    // Méthode pour ajouter une nouvelle voiture
    public void ajouterVoiture(Voiture voiture) {
        voitures.add(voiture);
        System.out.println("Voiture ajoutée avec succès.");
    }

    // Méthode pour supprimer une voiture existante
    public void supprimerVoiture(Voiture voiture) {
        if (voitures.remove(voiture)) {
            System.out.println("Voiture supprimée avec succès.");
        } else {
            System.out.println("La voiture n'a pas été trouvée dans la liste.");
        }
    }

    // Méthode pour afficher toutes les voitures disponibles
    public void afficherVoitures() {
        System.out.println("Liste des voitures disponibles :");
        for (Voiture voiture : voitures) {
            System.out.println(voiture.toString());
        }
    }
}

