package Controller;

import Model.Voiture;
import DAO.VoitureDAO;
import DAO.VoitureDAOImpl;
import java.sql.SQLException;
import java.util.*;

public class VoitureController {
    private List<Voiture> voitures;
    private VoitureDAOImpl voitureDAOImpl;

    public VoitureController(VoitureDAOImpl voitureDAOImpl) {
        this.voitureDAOImpl = voitureDAOImpl;
        this.voitures = voitureDAOImpl.getAllVoitures();
    }

    // Méthode pour ajouter une voiture à la liste et à la base de données
    public void ajouterVoiture(String voitureID, String modele, String marque, int type, float prix,boolean Disponibilite,String photo) {
        for (Voiture v : voitures) {
            if (v.getVoitureID().equals(voitureID)) {
                System.out.println("Une voiture avec le même ID existe déjà dans la liste.");
                return;
            }
        }

        Voiture nouvelleVoiture = new Voiture(voitureID, modele, marque, type, prix,true,photo);

        voitures.add(nouvelleVoiture);
        voitureDAOImpl.ajouterVoiture(nouvelleVoiture);
        System.out.println("Voiture ajoutée avec succès : " + nouvelleVoiture);
    }
    public boolean voitureHasReservations(String voitureId) throws SQLException {
        return voitureDAOImpl.voitureHasReservations(voitureId);
    }
    public void setVehicleUnavailable(String vehicleId) {
        voitureDAOImpl.setVehicleUnavailable(vehicleId);
    }
    public Voiture getVoitureById(String voitureId) {
        for (Voiture voiture : voitures) {
            if (voiture.getVoitureID().equals(voitureId)) {
                return voiture;
            }
        }
        return null; // Return null if the car with the given ID is not found
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
        voitureDAOImpl.modifierDisponibiliteVoiture(id, disponible);

        // Modifier la disponibilité de la voiture dans la liste de voitures du contrôleur
        for (Voiture voiture : voitures) {
            if (voiture.getVoitureID().equals(id)) {
                voiture.setDisponibilite(disponible);
                System.out.println("La disponibilité de la voiture a été modifiée avec succès dans le contrôleur.");
                break;
            }
        }
    }



    public void supprimerVoiture(String voitureId) {
        // Recherche de la voiture dans la liste par son ID
        Voiture voitureToRemove = null;
        for (Voiture voiture : voitures) {
            if (voiture.getVoitureID().equals(voitureId)) {
                voitureToRemove = voiture;
                break;
            }
        }

        if (voitureToRemove != null) {
            // Supprimer la voiture de la base de données
            voitureDAOImpl.supprimerVoiture(voitureId);

            // Supprimer la voiture de la liste de voitures du contrôleur
            if (voitures.remove(voitureToRemove)) {
                System.out.println("La voiture a été supprimée avec succès dans le controller");
            } else {
                System.out.println("La voiture avec l'ID " + voitureId + " n'a pas été trouvée dans la liste.");
            }
        } else {
            System.out.println("La voiture avec l'ID " + voitureId + " n'a pas été trouvée dans la liste.");
        }
    }

    public List<Voiture> getVoituresDisponibles() {
        return voitureDAOImpl.getVoituresDisponibles();
    }


    public List<Voiture> getVoitures() {
        return voitures; // Retourne la liste des voitures gérées par le contrôleur
    }

    public Map<String, Integer> getTypeCount() {
        return voitureDAOImpl.getTypeCount();
    }

    // Méthode pour accéder à VoitureDAO depuis d'autres classes



    public VoitureDAOImpl getVoitureDAO() {
        return this.voitureDAOImpl;
    }



    @Override
    public String toString() {
        return "VoitureController{" +
                "voitures=" + voitures +
                '}';
    }
}
