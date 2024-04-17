package DAO;

import Model.Voiture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VoitureDAOImpl implements VoitureDAO {

    private Connection connection;


    @Override
    public void connect(String URLDataBase, String LoginDataBase, String PwdDataBase) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URLDataBase, LoginDataBase, PwdDataBase);
    }

    @Override
    public List<Voiture> getVoituresDisponibles() {
        List<Voiture> voitures = new ArrayList<>();
        String query = "SELECT * FROM Voiture WHERE Disponibilite = true";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Voiture voiture = new Voiture();
                voiture.setVoitureID(resultSet.getString("voitureID"));
                voiture.setMarque(resultSet.getString("Marque"));
                voiture.setModele(resultSet.getString("Modele"));
                voiture.setDisponibilite(resultSet.getBoolean("Disponibilite"));
                voiture.setType(resultSet.getInt("Type")); // Ajout de Type
                voiture.setPrix(resultSet.getFloat("Prix_j")); // Ajout de Prix_j
                voiture.setPhoto(resultSet.getString("photo"));

                // Set other car attributes

                voitures.add(voiture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return voitures;
    }


    @Override
    public List<Voiture> getAllVoitures() {
        List<Voiture> voitures = new ArrayList<>();
        String query = "SELECT * FROM Voiture";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Voiture voiture = new Voiture();
                voiture.setVoitureID(resultSet.getString("voitureID"));
                voiture.setMarque(resultSet.getString("Marque"));
                voiture.setModele(resultSet.getString("Modele"));
                voiture.setDisponibilite(resultSet.getBoolean("Disponibilite"));
                voiture.setType(resultSet.getInt("Type")); // Ajout de Type
                voiture.setPrix(resultSet.getFloat("Prix_j")); // Ajout de Prix_j
                voiture.setPhoto(resultSet.getString("photo"));

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
        String query = "SELECT * FROM Voiture WHERE VoitureID = ?";
        System.out.println(connection);
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Voiture voiture = new Voiture();
                    voiture.setVoitureID(resultSet.getString("VoitureID"));
                    voiture.setMarque(resultSet.getString("Marque"));
                    voiture.setModele(resultSet.getString("Modele"));
                    voiture.setDisponibilite(resultSet.getBoolean("Disponibilite"));
                    voiture.setType(resultSet.getInt("Type")); // Ajout de Type
                    voiture.setPrix(resultSet.getFloat("Prix_j")); // Ajout de Prix_j
                    voiture.setPhoto(resultSet.getString("photo"));

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
        String query = "INSERT INTO Voiture (voitureID, Marque, Modele, Disponibilite, Type, Prix_j) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, voiture.getVoitureID());
            statement.setString(2, voiture.getMarque());
            statement.setString(3, voiture.getModele());
            statement.setBoolean(4, voiture.isDisponible());
            statement.setInt(5, voiture.getType()); // Ajout de Type
            statement.setFloat(6, voiture.getPrix()); // Ajout de Prix_j
            statement.setString(7, voiture.getPhoto()); // Ajout de Prix_j
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
        String query = "UPDATE Voiture SET Disponibilite = ? WHERE voitureID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, disponible);
            statement.setString(2, id);

            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("La disponibilité de la voiture a été modifiée avec succès dans la base de données.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    @Override
    public String toString() {
        return "VoitureDAOImpl{" +
                "connection=" + connection +
                '}';
    }
}
