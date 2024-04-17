package DAO;

import Model.Client;
import Model.Reservation;
import Model.Voiture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDAOImpl implements ClientDAO {
    private Connection connection;

    @Override
    public void connect(String URLDataBase, String LoginDataBase, String PwdDataBase) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URLDataBase, LoginDataBase, PwdDataBase);
    }

    @Override
    public List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM Client";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Client client = new Client();
                client.setMail(resultSet.getString("Email"));
                client.setMdp(resultSet.getString("mdp"));
                client.setPrenom(resultSet.getString("Prenom"));
                client.setNom(resultSet.getString("Nom"));
                client.setType(resultSet.getInt("Type"));
                client.setStatutMembre(resultSet.getBoolean("StatutMembre"));
                clients.add(client);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }


    @Override
    public Client getClientByEmail(String email) {
        Client client = null;
        String query = "SELECT * FROM Client WHERE Email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    client = new Client();
                    client.setMail(resultSet.getString("Email"));
                    client.setPrenom(resultSet.getString("Prenom"));
                    client.setNom(resultSet.getString("Nom"));
                    client.setType(resultSet.getInt("Type"));
                    client.setStatutMembre(resultSet.getBoolean("StatutMembre"));
                    client.setReservations(getReservationsForClient(client.getMail()));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return client;
    }

    @Override
    public void insertClient(Client client) {
        String query = "INSERT INTO Client (Email, Prenom, Nom, Type, StatutMembre) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getMail());
            statement.setString(2, client.getPrenom());
            statement.setString(3, client.getNom());
            statement.setInt(4, client.getType());
            statement.setBoolean(5, client.isStatutMembre()); // Utilisation du type booléen

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Le client a été inséré avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void updateClient(Client client) {
        String query = "UPDATE Client SET Prenom = ?, Nom = ?, Type = ?, StatutMembre = ? WHERE Email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getPrenom());
            statement.setString(2, client.getNom());
            statement.setInt(3, client.getType());
            statement.setBoolean(4, client.isStatutMembre());
            statement.setString(5, client.getMail());

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Les informations du client ont été mises à jour avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteClient(Client client) {
        String query = "DELETE FROM Client WHERE Email = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, client.getMail());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Le client a été supprimé avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reservation> getReservationsForClient(String email) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation WHERE ClientEmail = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setBookID(resultSet.getInt("bookID"));
                    reservation.setMailClient(email);
                    // Récupérer les informations de la voiture associée à cette réservation
                    String voitureId = resultSet.getString("VoitureID");
                    //Voiture voiture = getVoitureById(voitureId); // Méthode à implémenter dans VoitureDAOImpl
                    //reservation.setVoiture(voiture);
                    reservation.setDateDebut(resultSet.getDate("DateDebut").toLocalDate());
                    reservation.setDateFin(resultSet.getDate("DateFin").toLocalDate());
                    // Ajouter la réservation à la liste
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }


    @Override
    public void updateReservation(Client client, Reservation reservation) {
        // Supprimer toutes les réservations existantes pour ce client
        deleteReservationsForClient(client.getMail());
        // Insérer les nouvelles réservations dans la base de données
        insertReservations(reservation);
    }

    public void deleteReservationsForClient(String email) {
        String query = "DELETE FROM Reservation WHERE ClientEmail = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void insertReservations(Reservation reservation) {
        String query = "INSERT INTO Reservation (VoitureID, ClientID, DateDebut, Montant, DateFin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reservation.getVoiture().getVoitureID());
            statement.setString(2, reservation.getMailClient());
            statement.setDate(3, java.sql.Date.valueOf(reservation.getDateDebut()));
            statement.setInt(4, reservation.getMontant());
            statement.setDate(5, java.sql.Date.valueOf(reservation.getDateFin()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("La réservation a été ajoutée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
