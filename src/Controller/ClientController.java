package Controller;

import Model.Client;
import Model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ClientController {
    private List<Client> clients;

    public ClientController() {
        this.clients = new ArrayList<>();
    }

    // Méthode pour ajouter un client à la liste
    public void ajouterClient(Client client) {
        clients.add(client);
        System.out.println("Client ajouté avec succès : " + client);
    }

    // Méthode pour supprimer un client de la liste
    public void supprimerClient(Client client) {
        if (clients.remove(client)) {
            System.out.println("Client supprimé avec succès : " + client);
        } else {
            System.out.println("Le client n'a pas été trouvé dans la liste.");
        }
    }

    // Méthode pour afficher tous les clients de la liste
    public void afficherClients() {
        if (clients.isEmpty()) {
            System.out.println("Aucun client disponible.");
        } else {
            System.out.println("Liste des clients :");
            for (Client client : clients) {
                System.out.println(client.toString());
            }
        }
    }

    // Méthode pour ajouter une réservation à un client
    public void ajouterReservation(Client client,Reservation reservation) {
        if (clients.contains(client)) {
            client.ajouterReservation(reservation);
        } else {
            System.out.println("Le client n'existe pas dans la liste.");
        }
    }

    @Override
    public String toString() {
        return "ClientController{" +
                "clients=" + clients +
                '}';
    }

    // Autres méthodes pour gérer les opérations sur les clients et leurs réservations
}

