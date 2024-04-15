import Model.Agence;
import Model.Client;
import Model.Reservation;
import Model.Voiture;
import java.time.LocalDate;


public class Test {
    public static void main(String[] args) {
        Agence agence = new Agence("location de voitures");
        Voiture voiture = new Voiture("12332","urus","lamborghini",1,2122);
        Voiture voiture2 = new Voiture("rerwe","roejr","lamrereborghini",1,2122);
        Client client = new Client("ugjg@gmail.com","464665","Martin","de Peretti",1,true);
        agence.ajoutVehicule(voiture);
        agence.ajoutVehicule(voiture2);
        agence.ajoutClient(client);
        Reservation resa = new Reservation(323,LocalDate.now(),LocalDate.now(),voiture );
        client.ajouterReservation(resa);
        //resa.RetourVoiture();
        System.out.println(agence.toString());
    }
}
