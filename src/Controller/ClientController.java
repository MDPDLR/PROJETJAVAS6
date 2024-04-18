package Controller;

import DAO.ClientDAO;

import Model.Client;
import Model.Reservation;
import Model.Voiture;

import java.util.ArrayList;
import java.util.List;
public class ClientController {
    private List<Client> clients;
    private ClientDAO clientDAO;

    public ClientController(ClientDAO clientDAO) {
        this.clientDAO = clientDAO;
        this.clients = clientDAO.getAllClients();
    }

    // Méthode pour ajouter un client à la liste et à la base de données
    public void ajouterClient(String mail, String mdp, String prenom, String nom, int type, boolean statutMembre) {
        // Vérifier si le client existe déjà dans la liste avec la même adresse e-mail
        for (Client c : clients) {
            if (c.getMail().equals(mail)) {
                System.out.println("Un client avec la même adresse e-mail existe déjà dans la liste.");
                return;
            }
        }

        // Créer le client avec les informations fournies
        Client nouveauClient = new Client(mail, mdp, prenom, nom, type, statutMembre);

        // Ajouter le client à la liste et à la base de données
        clients.add(nouveauClient);
        clientDAO.insertClient(nouveauClient);
        System.out.println("Client ajouté avec succès : " + nouveauClient);
    }



    // Méthode pour supprimer un client de la liste et de la base de données


    public void supprimerClient(String clientId) {
        // Recherche du client dans la liste par son ID
        Client clientToRemove = null;
        for (Client client : clients) {
            if (client.getMail().equals(clientId)) {
                clientToRemove = client;
                break;
            }
        }

        if (clientToRemove != null) {
            // Supprimer le client de la base de données
            clientDAO.deleteClient(clientToRemove);

            // Supprimer le client de la liste des clients du contrôleur
            if (clients.remove(clientToRemove)) {
                System.out.println("Le client a été supprimé avec succès dans le controller");
            } else {
                System.out.println("Le client avec l'ID " + clientId + " n'a pas été trouvé dans la liste.");
            }
        } else {
            System.out.println("Le client avec l'ID " + clientId + " n'a pas été trouvé dans la liste.");
        }
    }
    public Client getClientByMail(String mail) {
        for (Client client : clients) {
            if (client.getMail().equals(mail)) {
                return client;
            }
        }
        return null; // Retourne null si aucun client n'est trouvé avec l'adresse e-mail spécifiée
    }

    public void modifierStatutMembre(String clientId, boolean nouveauStatut) {
        // Recherche du client dans la liste par son ID
        Client clientToModify = null;
        for (Client client : clients) {
            if (client.getMail().equals(clientId)) {
                clientToModify = client;
                break;
            }
        }

        if (clientToModify != null) {
            // Modifier le statut membre du client
            clientToModify.setStatutMembre(nouveauStatut);

            // Mettre à jour le client dans la base de données
            clientDAO.updateClient(clientToModify);

            System.out.println("Le statut membre du client a été modifié avec succès.");
        } else {
            System.out.println("Le client avec l'ID " + clientId + " n'a pas été trouvé dans la liste.");
        }
    }

    public void ajouterReservation(Client client, Reservation reservation) {
        client.ajouterReservation(reservation); // Ajouter la réservation à la liste des réservations du client
        clientDAO.updateClient(client); // Mettre à jour le client dans la base de données
        System.out.println("Réservation ajoutée avec succès pour le client : " + client.getMail());
    }
    // Méthode pour mettre à jour les informations d'un client dans la liste et dans la base de données
    @Override
    public String toString() {
        return "ClientController{" +
                "clients=" + clients +
                '}';
    }
}



    // Autres méthodes pour gérer les opérations sur les clients et leurs réservations


