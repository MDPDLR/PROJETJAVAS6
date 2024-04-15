package Model;

public class Client {
    private String mail;
    private String mdp;
    private String prenom;
    private String nom;
    private int type;
    private boolean statutMembre;

    // Getters et setters

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
        if (this.mail.equals(mail) && this.mdp.equals(mdp)) {
            return true; // Connexion réussie
        } else {
            return false; // Échec de la connexion
        }
    }

    public void louer(Voiture voiture) {
        if (voiture.isDisponible()) {
            voiture.louer(); // La voiture est maintenant louée
        } else {
            System.out.println("Désolé, cette voiture n'est pas disponible.");
        }
    }
}