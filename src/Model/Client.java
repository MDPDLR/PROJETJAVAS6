package Model;

public class Client {
    private String mail;
    private String mdp;
    private String prenom;
    private String nom;
    private int type;
    private boolean statutMembre;

    // Constructeur
    public Client() {
        // Constructeur vide
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

    public void inscriptionClient(String mail, String mdp, String prenom, String nom, int type) {
        this.mail = mail;
        this.mdp = mdp;
        this.prenom = prenom;
        this.nom = nom;
        this.type = type;
        this.statutMembre = false; // Par défaut, un nouveau client n'est pas un membre
    }

    public boolean seConnecter(String mail, String mdp) {
        return this.mail.equals(mail) && this.mdp.equals(mdp);
    }

    public void louer(Voiture voiture) {
        if (voiture.isDisponible()) {
            voiture.louer(); // La voiture est maintenant louée
        } else {
            System.out.println("Désolé, cette voiture n'est pas disponible.");
        }
    }
}
