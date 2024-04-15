package Model;

public class Voiture {
    private int voitureID;
    private String modele;
    private String marque;
    private boolean type;
    private float prix;
    private boolean disponibilite;

    public Voiture(int voitureID, String modele, String marque, boolean type, float prix, boolean disponibilite) {
        this.voitureID = voitureID;
        this.modele = modele;
        this.marque = marque;
        this.type = type;
        this.prix = prix;
        this.disponibilite = disponibilite;
    }

    // Getters et setters

    public boolean isDisponible() {
        return this.disponibilite;
    }

    public void louer() {
        this.disponibilite = false;
    }

    public void retour() {
        this.disponibilite = true;
    }
}
