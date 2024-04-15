package Model;
import java.time.LocalDate;

public class Reservation {
    private int bookID;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int montant;
    private Voiture voiture;

    public Reservation() {
        // Initialisation des attributs avec des valeurs par défaut
        this.bookID = 0;
        this.dateDebut = null;
        this.dateFin = null;
        this.montant = 0;
        this.voiture = null;
    }

    public Reservation(int bookID, LocalDate dateDebut, LocalDate dateFin, Voiture voiture) {
        this.bookID = bookID;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.voiture = voiture;
        calculMontant();
        voiture.setDisponibilite(false);
    }

    public void RetourVoiture(){
        LocalDate currentDate = LocalDate.now();
        if(this.dateFin.equals(currentDate)){
            this.voiture.setDisponibilite(true);
        }
    }

    // Getters et setters

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDuree(LocalDate dateFin) {
        this.dateFin = dateFin;
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
    private void calculMontant() {
        long diffDays = java.time.temporal.ChronoUnit.DAYS.between(dateDebut, dateFin);
        this.montant = (int) (diffDays * voiture.getPrix());
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "bookID=" + bookID +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", montant=" + montant +
                ", voiture=" + voiture +
                '}';
    }
}
