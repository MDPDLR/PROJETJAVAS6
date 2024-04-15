import Model.Client;
import Model.Voiture;

public class Test {
    public static void main(String[] args) {
        Voiture voiture = new Voiture("12332","urus","lamborghini",1,2122,true);
        System.out.print(voiture.toString());
    }
}
