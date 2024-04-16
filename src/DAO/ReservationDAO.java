package DAO;

import java.util.List;

public interface ReservationDAO {
    List<Reservation> getAllReservations();
    List<Reservation> getReservationsByClient(String email);
    void addReservation(Reservation reservation);
    void updateReservation(Reservation reservation);
    void deleteReservation(int id);
}
