package Model;

public class Voiture {
    private String voitureID;
    private String modele;
    private String marque;
    private int type;
    private float prix;
    private boolean disponibilite;
    private String photo;
    public Voiture(){
        this.voitureID = null;
    }
    public Voiture(String voitureID, String modele, String marque, int type, float prix) {
        this.voitureID = voitureID;
        this.modele = modele;
        this.marque = marque;
        this.type = type;
        this.prix = prix;
        this.disponibilite = true;
        this.photo = type +".png";
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

    public int  isType() {
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

    public int getType() {
        return type;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "Voiture{" +
                "voitureID='" + voitureID + '\'' +
                ", modele='" + modele + '\'' +
                ", marque='" + marque + '\'' +
                ", type=" + type +
                ", prix=" + prix +
                ", disponibilite=" + disponibilite +
                ", photo='" + photo + '\'' +
                '}';
    }
}
