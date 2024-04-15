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

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getMontant() {
        return montant;
    }

    public void setMontant(int montant) {
        this.montant = montant;
    }

    public void calculMontant(Voiture voiture) {
        if (voiture != null) {
            this.montant = (int) (voiture.getPrix() * this.duree);
        }
    }
}
