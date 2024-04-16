package DAO;

import java.util.ArrayList;
import java.util.List;
import Model.Voiture;

import java.sql.*;
public class VoitureDAOImpl implements VoitureDAO {
    private final String URL = "jdbc:mysql://localhost:3306/location_voiture";
    private final String USERNAME = "utilisateur";
    private final String PASSWORD = "motdepasse";

    @Override
    public List<Voiture> getVoituresDisponibles() {
        List<Voiture> voitures = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM voitures WHERE disponible = true");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Voiture voiture = resultSetToVoiture(resultSet);
                voitures.add(voiture);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return voitures;
    }

    @Override
    public Voiture getVoitureById(String id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM voitures WHERE id = ?");
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSetToVoiture(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void ajouterVoiture(Voiture voiture) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("INSERT INTO voitures (id, marque, modele, type, prix_par_jour, disponible) VALUES (?, ?, ?, ?, ?, ?)")) {
            statement.setString(1, voiture.getVoitureID());
            statement.setString(2, voiture.getMarque());
            statement.setString(3, voiture.getModele());
            statement.setString(4, voiture.getModele());
            statement.setDouble(5, voiture.getPrix());
            statement.setBoolean(6, voiture.isDisponible());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerVoiture(String id) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("DELETE FROM voitures WHERE id = ?")) {
            statement.setString(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void modifierDisponibiliteVoiture(String id, boolean disponible) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("UPDATE voitures SET disponible = ? WHERE id = ?")) {
            statement.setBoolean(1, disponible);
            statement.setString(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Voiture resultSetToVoiture(ResultSet resultSet) throws SQLException {
        Voiture voiture = new Voiture();
        voiture.setVoitureID(resultSet.getString("id"));
        voiture.setMarque(resultSet.getString("marque"));
        voiture.setModele(resultSet.getString("modele"));
        voiture.setType(resultSet.getString("type"));
        voiture.setPrix(resultSet.getFloat("prix_par_jour"));
        voiture.setDisponibilite(resultSet.getBoolean("disponible"));
        return voiture;
    }
}

