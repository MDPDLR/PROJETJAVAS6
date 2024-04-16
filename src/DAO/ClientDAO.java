package DAO;
import java.util.List;

public interface ClientDAO {
    Client getClientByEmail(String email);
    void insertClient(Client client);
    void updateClient(Client client);
}
