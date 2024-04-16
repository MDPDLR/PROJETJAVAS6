package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    private Connection connection;

    public ReservationDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Reservation> getAllReservations() {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation";
        
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Reservation reservation = new Reservation();
                reservation.setId(resultSet.getInt("ID"));
                reservation.setClientEmail(resultSet.getString("ClientEmail"));
                reservation.setCarId(resultSet.getInt("CarID"));
                reservation.setStartDate(resultSet.getDate("StartDate"));
                reservation.setEndDate(resultSet.getDate("EndDate"));
                // Set other reservation attributes
                
                reservations.add(reservation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return reservations;
    }

    @Override
    public List<Reservation> getReservationsByClient(String email) {
        List<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM Reservation WHERE ClientEmail = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Reservation reservation = new Reservation();
                    reservation.setId(resultSet.getInt("ID"));
                    reservation.setClientEmail(resultSet.getString("ClientEmail"));
                    reservation.setCarId(resultSet.getInt("CarID"));
                    reservation.setStartDate(resultSet.getDate("StartDate"));
                    reservation.setEndDate(resultSet.getDate("EndDate"));
                    // Set other reservation attributes
                    
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return reservations;
    }

    @Override
    public void addReservation(Reservation reservation) {
        String query = "INSERT INTO Reservation (ClientEmail, CarID, StartDate, EndDate) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reservation.getClientEmail());
            statement.setInt(2, reservation.getCarId());
            statement.setDate(3, reservation.getStartDate());
            statement.setDate(4, reservation.getEndDate());
            
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("La réservation a été ajoutée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateReservation(Reservation reservation) {
        String query = "UPDATE Reservation SET ClientEmail = ?, CarID = ?, StartDate = ?, EndDate = ? WHERE ID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, reservation.getClientEmail());
            statement.setInt(2, reservation.getCarId());
            statement.setDate(3, reservation.getStartDate());
            statement.setDate(4, reservation.getEndDate());
            statement.setInt(5, reservation.getId());
            
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Les informations de la réservation ont été mises à jour avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteReservation(int id) {
        String query = "DELETE FROM Reservation WHERE ID = ?";
        
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, id);
            
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("La réservation a été supprimée avec succès.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
