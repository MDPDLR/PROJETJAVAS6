package DAO;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import Model.Voiture;

public interface VoitureDAO {
    List<Voiture> getVoituresDisponibles();

    public void connect(String URLDataBase, String LoginDataBase, String PwdDataBase) throws SQLException, ClassNotFoundException;

    Voiture getVoitureById(String id);

    void ajouterVoiture(Voiture voiture);

    void supprimerVoiture(String id);

    void modifierDisponibiliteVoiture(String id, boolean disponible);

    public List<Voiture> getAllVoitures();

    boolean voitureHasReservations(String voitureId) throws SQLException;

    void setVehicleUnavailable(String vehicleId);
    Map<String, Integer> getTypeCount();
}
