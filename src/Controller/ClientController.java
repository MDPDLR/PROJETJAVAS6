package Controller;

import Model.Voiture;

public class ClientController {
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
