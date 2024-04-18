package DAO;

import Model.Reservation;
import Model.Voiture;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private Connection connection;
    @Override
    public void connect(String URLDataBase, String LoginDataBase, String PwdDataBase) throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        connection = DriverManager.getConnection(URLDataBase, LoginDataBase, PwdDataBase);
    }

    @Override
    public void ajouterReservation(Reservation reservation) {
        String query = "INSERT INTO Reservation (VoitureID, ClientID, DateDebut, Montant, DateFin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reservation.getVoiture().getVoitureID());
            statement.setString(2, reservation.getMailClient());
            statement.setDate(3, java.sql.Date.valueOf(reservation.getDateDebut()));
            statement.setFloat(4, reservation.getMontant());
            statement.setDate(5, java.sql.Date.valueOf(reservation.getDateFin()));

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("La réservation a été ajoutée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void supprimerReservation(Reservation reservation) {
        String query = "DELETE FROM Reservation WHERE bookID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, reservation.getBookID());

            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La réservation a été supprimée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation";

        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setBookID(resultSet.getInt("bookID"));
                reservation.setMailClient(resultSet.getString("ClientID"));
                reservation.setVoiture(getVoitureById(resultSet.getString("VoitureID")));
                reservation.setDateDebut(resultSet.getDate("DateDebut").toLocalDate());
                reservation.setMontant(resultSet.getInt("Montant"));
                reservation.setDateFin(resultSet.getDate("DateFin").toLocalDate());
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    @Override
    public List<Reservation> getReservationsForClient(String email) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation WHERE ClientID = ?";

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setBookID(resultSet.getInt("bookID"));
                    reservation.setMailClient(email);
                    reservation.setVoiture(getVoitureById(resultSet.getString("VoitureID")));
                    reservation.setDateDebut(resultSet.getDate("DateDebut").toLocalDate());
                    reservation.setMontant(resultSet.getInt("Montant"));
                    reservation.setDateFin(resultSet.getDate("DateFin").toLocalDate());
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return reservations;
    }

    // Méthode pour obtenir une voiture par son ID
    public Voiture getVoitureById(String id) {
        VoitureDAOImpl voitureDAOImpl = new VoitureDAOImpl();
        try {
            voitureDAOImpl.connect("jdbc:mysql://localhost:8889/Projet-LocationDeVoiture", "root", "root");

        }
    catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
        return voitureDAOImpl.getVoitureById(id);
    }

    @Override
    public String toString() {
        return "ReservationDAOImpl{" +
                "connection=" + connection +
                '}';
    }
}
