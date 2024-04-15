package Model;
import java.util.Date;

public class Reservation {
    private int bookID;
    private Date dateDebut;
    private int duree;
    private int montant;
    private Voiture voiture;
    private Client client;

    public Reservation(int bookID, Date dateDebut, int duree, Voiture voiture, Client client) {
        this.bookID = bookID;
        this.dateDebut = dateDebut;
        this.duree = duree;
        this.montant = (int)(voiture.getPrix() * this.duree);
        this.voiture = voiture;
        this.client = client;
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

    // Getter et setter pour l'attribut Voiture

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }

}
