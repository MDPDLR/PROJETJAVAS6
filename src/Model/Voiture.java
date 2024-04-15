package Model;

public class Voiture {
    private int voitureID;
    private String modele;
    private String marque;
    private int type;
    private float prix;
    private boolean disponibilite;

    public Voiture(int voitureID, String modele, String marque, int type, float prix, boolean disponibilite) {
        this.voitureID = voitureID;
        this.modele = modele;
        this.marque = marque;
        this.type = type;
        this.prix = prix;
        this.disponibilite = disponibilite;
    }

    // Getters et setters

    public int getVoitureID() {
        return voitureID;
    }

    public void setVoitureID(int voitureID) {
        this.voitureID = voitureID;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public int isType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public boolean isDisponible() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public void louer() {
        this.disponibilite = false;
    }

    public void retour() {
        this.disponibilite = true;
    }
}
