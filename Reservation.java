package Model;
import java.time.LocalDate;

public class Reservation {
    private int bookID;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private float montant;
    private Voiture voiture;
    private String mailClient;

    public Reservation() {
        // Initialisation des attributs avec des valeurs par défaut
        this.dateDebut = null;
        this.dateFin = null;
        this.montant = 0;
        this.voiture = null;
    }

    public Reservation(LocalDate dateDebut, LocalDate dateFin, Voiture voiture,Client client) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.voiture = voiture;
        this.mailClient = client.getMail();
        this.montant =  (this.diffDays(dateDebut,dateFin)*this.voiture.getPrix()) ;
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

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    // Getter et setter pour l'attribut Voiture

    public Voiture getVoiture() {
        return voiture;
    }

    public void setVoiture(Voiture voiture) {
        this.voiture = voiture;
    }
    private int diffDays(LocalDate dateDebut, LocalDate dateFin) {
        long diffDays = java.time.temporal.ChronoUnit.DAYS.between(dateDebut, dateFin);
        return (int) diffDays;
    }

    public void setMailClient(String mailClient) {
        this.mailClient = mailClient;
    }

    public String getMailClient() {
        return mailClient;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "bookID=" + bookID +
                ", dateDebut=" + dateDebut +
                ", dateFin=" + dateFin +
                ", montant=" + montant +
                ", voiture=" + voiture +
                ", mailClient='" + mailClient + '\'' +
                '}';
    }
}
