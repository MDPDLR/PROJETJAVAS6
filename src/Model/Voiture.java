package Model;

public class Voiture {
    private String voitureID;
    private String modele;
    private String marque;
    private String type;
    private float prix;
    private boolean disponibilite;

    public Voiture(){
        this.voitureID = null;
    }
    public Voiture(String voitureID, String modele, String marque, String type, float prix) {
        this.voitureID = voitureID;
        this.modele = modele;
        this.marque = marque;
        this.type = type;
        this.prix = prix;
        this.disponibilite = true;
    }


    public String getVoitureID() {
        return voitureID;
    }

    public void setVoitureID(String voitureID) {
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

    public String  isType() {
        return type;
    }

    public void setType(String type) {
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

    public String getType() {
        return type;
    }



    @Override
    public String toString() {
        return "Voiture{" +
                "voitureID=" + voitureID +
                ", modele='" + modele + '\'' +
                ", marque='" + marque + '\'' +
                ", type=" + type +
                ", prix=" + prix +
                ", disponibilite=" + disponibilite +
                '}';
    }
}
