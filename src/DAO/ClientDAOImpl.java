package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientDAOImpl implements ClientDAO {
    private Connection connection;

    public ClientDAOImpl(Connection connection) {
        this.connection = connection;
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
                    client.setEmail(resultSet.getString("Email"));
                    client.setPrenom(resultSet.getString("Prenom"));
                    // Set other client attributes
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return client;
    }

    @Override
    insertClient(Client client) :

@Override
public void insertClient(Client client) {
    String query = "INSERT INTO Client (Email, Prenom, Nom, Type) VALUES (?, ?, ?, ?)";
    
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, client.getEmail());
        statement.setString(2, client.getPrenom());
        statement.setString(3, client.getNom());
        statement.setString(4, client.getType());
        
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
    String query = "UPDATE Client SET Prenom = ?, Nom = ?, Type = ? WHERE Email = ?";
    
    try (PreparedStatement statement = connection.prepareStatement(query)) {
        statement.setString(1, client.getPrenom());
        statement.setString(2, client.getNom());
        statement.setString(3, client.getType());
        statement.setString(4, client.getEmail());
        
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("Les informations du client ont été mises à jour avec succès.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
