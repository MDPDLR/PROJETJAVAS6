import Controller.ClientController;
import Controller.ReservationController;
import Controller.VoitureController;
import Model.Client;
import Model.Reservation;
import Model.Voiture;
import java.time.LocalDate;


public class Test {
    public static void main(String[] args) {
        VoitureController voitureController = new VoitureController();
        ClientController clientController = new ClientController();
        ReservationController reservationController = new ReservationController();
        Voiture voiture = new Voiture("12332","urus","lamborghini","SUV",2122);
        Voiture voiture2 = new Voiture("rerwe","roejr","lamrereborghini","Berline",2122);
        voitureController.ajouterVoiture(voiture);
        voitureController.ajouterVoiture(voiture2);
        Client client = new Client("ugjg@gmail.com","464665","Martin","de Peretti",1,true);
        clientController.ajouterClient(client);
        Reservation resa = new Reservation(323,LocalDate.now(),LocalDate.now(),voiture );
        reservationController.ajouterReservation(resa);
        clientController.ajouterReservation(client,resa);

        reservationController.afficherReservations();
        //clientController.afficherClients();
    }
}
