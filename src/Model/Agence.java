package Model;

import java.util.List;
import java.util.ArrayList;

public class Agence {

    private String nom;
    private List<Admin> admins = new ArrayList<>();
    private List<Voiture> voitures = new ArrayList<>();

    // Getters et setters
    public Agence(String nom) {
        this.nom = nom;
        this.admins = new ArrayList<>();
        this.voitures = new ArrayList<>();
    }

    public void ajouterAdmin(Admin admin) {
        this.admins.add(admin);
    }

    public void ajoutVehicule(Voiture voiture) {
        this.voitures.add(voiture);
    }
    public void mofierVehicule(int voitureID, String modele, String marque, String type, float prix, boolean disponibilite) {
        for (Voiture voiture : this.voitures) {
            if (voiture.getVoitureID() == voitureID) {
                voiture.setModele(modele);
                voiture.setMarque(marque);
                voiture.setType(type);
                voiture.setPrix(prix);
                voiture.setDisponibilite(disponibilite);
                break;
            }
        }
    }

    public void retirerVehicule(int voitureID) {
        this.voitures.removeIf(voiture -> voiture.getVoitureID() == voitureID);
    }

    public boolean connexionAdmin(int id, String mdp) {
        for (Admin admin : this.admins) {
            if (admin.verifierIdentifiants(id, mdp)) {
                return true; // Connexion réussie
            }
        }
        return false; // Échec de la connexion
    }
}