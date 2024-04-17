package DAO;
import Model.Client;
import Model.Reservation;

import java.sql.SQLException;
import java.util.List;

public interface ClientDAO {
    public void connect(String URLDataBase, String LoginDataBase, String PwdDataBase) throws SQLException, ClassNotFoundException;
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
