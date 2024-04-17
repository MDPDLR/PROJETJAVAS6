package Model;

public class Admin {
    private int idEmploye;
    private String nom;
    private String mdpEmploye;

    public Admin(int idEmploye, String mdpEmploye) {
        this.idEmploye = idEmploye;
        this.mdpEmploye = mdpEmploye;
    }


    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getMdpEmploye() {
        return mdpEmploye;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setMdpEmploye(String mdpEmploye) {
        this.mdpEmploye = mdpEmploye;
    }

    public boolean verifierIdentifiants(int id, String mdp) {
        return this.idEmploye == id && this.mdpEmploye.equals(mdp);
    }
}
