package Model;

import java.util.List;
import java.util.ArrayList;

public class Agence {

    private String nom;
    private List<Admin> admins;
    private List<Voiture> voitures;
    private List<Client> clients;

    // Constructeur
    public Agence(String nom) {
        this.nom = nom;
        this.admins = new ArrayList<>();
        this.voitures = new ArrayList<>();
        this.clients = new ArrayList<>();
    }

    // Méthodes

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public List<Admin> getAdmins() {
        return admins;
    }

    public void setAdmins(List<Admin> admins) {
        this.admins = admins;
    }

    public List<Voiture> getVoitures() {
        return voitures;
    }

    public void setVoitures(List<Voiture> voitures) {
        this.voitures = voitures;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void ajouterAdmin(Admin admin) {
        this.admins.add(admin);
    }

    public void ajoutVehicule(Voiture voiture) {
        this.voitures.add(voiture);
    }

    public void ajoutClient(Client client) {
        this.clients.add(client);
    }

    @Override
    public String toString() {
        return "Agence{" +
                "nom='" + nom + '\'' +
                ", \nadmins=" + admins +
                ", \nvoitures=" + voitures +
                ", \nclients=" + clients +
                '}';
    }
}
