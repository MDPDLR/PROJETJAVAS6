package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VoitureDAOImpl implements VoitureDAO {
    private Connection connection;

    public VoitureDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Voiture> getVoituresDisponibles() {
        List<Voiture> voitures = new ArrayList<>();
        String query = "SELECT * FROM Voiture WHERE Disponible = true";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Voiture voiture = new Voiture();
                voiture.setId(resultSet.getString("ID"));
                voiture.setMarque(resultSet.getString("Marque"));
                voiture.setModele(resultSet.getString("Modele"));
                voiture.setDisponible(resultSet.getBoolean("Disponible"));
                // Set other car attributes
                
                voitures.add(voiture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return voitures;
    }

    @Override
    public Voiture getVoitureById(String id) {
        String query = "SELECT * FROM Voiture WHERE ID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Voiture voiture = new Voiture();
                    voiture.setId(resultSet.getString("ID"));
                    voiture.setMarque(resultSet.getString("Marque"));
                    voiture.setModele(resultSet.getString("Modele"));
                    voiture.setDisponible(resultSet.getBoolean("Disponible"));
                    // Set other car attributes
                    
                    return voiture;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return null;
    }

    @Override
    public void ajouterVoiture(Voiture voiture) {
        String query = "INSERT INTO Voiture (ID, Marque, Modele, Disponible) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, voiture.getId());
            statement.setString(2, voiture.getMarque());
            statement.setString(3, voiture.getModele());
            statement.setBoolean(4, voiture.isDisponible());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("La voiture a été ajoutée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerVoiture(String id) {
        String query = "DELETE FROM Voiture WHERE ID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La voiture a été supprimée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifierDisponibiliteVoiture(String id, boolean disponible) {
        String query = "UPDATE Voiture SET Disponible = ? WHERE ID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, disponible);
            statement.setString(2, id);
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La disponibilité de la voiture a été modifiée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
