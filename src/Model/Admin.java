package Model;

public class Admin {
    private int idEmploye;
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

    public void setMdpEmploye(String mdpEmploye) {
        this.mdpEmploye = mdpEmploye;
    }

    public boolean verifierIdentifiants(int id, String mdp) {
        return this.idEmploye == id && this.mdpEmploye.equals(mdp);
    }
}
