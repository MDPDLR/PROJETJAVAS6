package Model;

import java.util.ArrayList;
import java.util.List;

public class Client {
    private String mail;
    private String mdp;
    private String prenom;
    private String nom;
    private int type;
    private boolean statutMembre;
    private List<Reservation> reservations;

    public Client() {
        this.mail = "";
        this.mdp = "";
        this.prenom = "";
        this.nom = "";
        this.type = 0;
        this.statutMembre = false;
        this.reservations = new ArrayList<>();
    }

    // Constructeur
    public Client(String mail, String mdp, String prenom, String nom, int type, boolean statutMembre) {
        this.mail = mail;
        this.mdp = mdp;
        this.prenom = prenom;
        this.nom = nom;
        this.type = type;
        this.statutMembre = statutMembre;
        this.reservations = new ArrayList<>();
    }


    // Getters et setters

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isStatutMembre() {
        return statutMembre;
    }

    public void setStatutMembre(boolean statutMembre) {
        this.statutMembre = statutMembre;
    }

    public boolean estMembre() {
        return this.statutMembre;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void ajouterReservation(Reservation reservation) {
        reservations.add(reservation);
    }


    @Override
    public String toString() {
        return "Client{" +
                "mail='" + mail + '\'' +
                ", mdp='" + mdp + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", type=" + type +
                ", statutMembre=" + statutMembre +
                ", reservations=" + reservations +
                '}';
    }
}
