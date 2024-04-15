package Model;

import java.util.Date;

public class Reservation {
    private int bookID;
    private Date dateDebut;
    private int duree;
    private int montant;

    public Reservation(int bookID, Date dateDebut, int duree) {
        this.bookID = bookID;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.montant = 0; // Initialisé à 0, sera calculé plus tard
    }

    // Getters et setters

    public void calculMontant(Voiture voiture) {
        if (voiture != null) {
            this.montant = (int) (voiture.getPrix() * this.duree);
        }
    }
}