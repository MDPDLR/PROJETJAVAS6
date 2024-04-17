package Controller;

import DAO.ClientDAO;

import Model.Client;
import Model.Reservation;

import java.util.ArrayList;
import java.util.List;
public class ClientController {
    private List<Client> clients;
    private ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clients = new ArrayList<>();
        this.clientDAO = clientDAO;
    }

    // Méthode pour ajouter un client à la liste et à la base de données
    public void ajouterClient(Client client) {
        clients.add(client);
        // Ajouter le client à la base de données
        clientDAO.insertClient(client);
        System.out.println("Client ajouté avec succès : " + client);
    }

    // Méthode pour supprimer un client de la liste et de la base de données
    public void supprimerClient(Client client) {
        clients.remove(client);
        // Supprimer le client de la base de données
        clientDAO.deleteClient(client);
        System.out.println("Client supprimé avec succès : " + client);
    }

    // Méthode pour afficher tous les clients de la liste
    public void afficherClients() {
        // Afficher les clients de la base de données
        List<Client> clientsFromDB = clientDAO.getAllClients();
        if (clientsFromDB.isEmpty()) {
            System.out.println("Aucun client disponible.");
        } else {
            System.out.println("Liste des clients :");
            for (Client client : clientsFromDB) {
                System.out.println(client.toString());
            }
        }
    }

    // Méthode pour mettre à jour les informations d'un client dans la liste et dans la base de données
    public void modifierInformationsClient(Client client) {
        // Mettre à jour les informations du client dans la liste
        // (cette étape peut ne pas être nécessaire selon ton cas d'utilisation)
        // ...

        // Mettre à jour les informations du client dans la base de données
        clientDAO.updateClient(client);
        System.out.println("Les informations du client ont été mises à jour avec succès.");
    }

    // Méthode pour ajouter une réservation à un client
    public void ajouterReservation(Client client, Reservation reservation) {
        // Ajouter la réservation au client dans la liste
        client.ajouterReservation(reservation);
        // Mettre à jour la réservation du client dans la base de données
        clientDAO.updateReservation(client, reservation);
    }
    @Override
    public String toString() {
        return "ClientController{" +
                "clients=" + clients +
                '}';
    }


    // Autres méthodes pour gérer les opérations sur les clients et leurs réservations
}



    // Autres méthodes pour gérer les opérations sur les clients et leurs réservations


