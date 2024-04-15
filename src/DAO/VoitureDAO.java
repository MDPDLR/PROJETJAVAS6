package DAO;

import java.util.List;
import Model.Voiture;

public interface VoitureDAO {
    List<Voiture> getVoituresDisponibles();
    Voiture getVoitureById(String id);
    void ajouterVoiture(Voiture voiture);
    void supprimerVoiture(String id);
    void modifierDisponibiliteVoiture(String id, boolean disponible);
}
