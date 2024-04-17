package DAO;
import Model.Client;
import Model.Reservation;

import java.util.List;

public interface ClientDAO {
    public List<Client> getAllClients();
    Client getClientByEmail(String email);
    void insertClient(Client client);
    void updateClient(Client client);
    public void deleteClient(Client client);
    public List<Reservation> getReservationsForClient(String email);
    public void updateReservation(Client client, Reservation reservation);
    public void deleteReservationsForClient(String email);
    public void insertReservations(Reservation reservation);

    }
