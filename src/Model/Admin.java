package Model;

public class Admin {
    private int idEmploye;
    private String mdpEmploye;

    public Admin(int idEmploye, String mdpEmploye) {
        this.idEmploye = idEmploye;
        this.mdpEmploye = mdpEmploye;
    }

    // Getters et setters

    public boolean verifierIdentifiants(int id, String mdp) {
        return this.idEmploye == id && this.mdpEmploye.equals(mdp);
    }
}