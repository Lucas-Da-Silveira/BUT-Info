import java.util.Date;

public class TestFerme {
    public static void main(String[] args) {

        Ferme ferme = new Ferme();

        ferme.setPrixJourPoulet(0.5);
        ferme.setPrixJourCanard(0.7);

        Poulet poulet1 = new Poulet(1, 2.5, Date(2016, 11, 1), 2.5);
        Poulet poulet2 = new Poulet(2, 2.5, Date(2016, 11, 1), 2.5);
        Poulet poulet3 = new Poulet(3, 2.5, Date(2016, 11, 1), 2.5);
        ferme.ajouterVolaille(poulet1);
        ferme.ajouterVolaille(poulet2);
        ferme.ajouterVolaille(poulet3);

        Canard canard1 = new Canard(4, 2.5, Date(2016, 11, 1), 2.5);
        Canard canard2 = new Canard(5, 2.5, Date(2016, 11, 1), 2.5);

        ferme.ajouterVolaille(canard1);
        ferme.ajouterVolaille(canard2);

        ferme.afficherVolailles();

        ferme.trierVolailleAAbattre()

        ferme.afficherVolaillesAAbatre();

        double prixTotal = ferme.evaluerPrixVolaillesAAbattre()
        System.out.println("Prix total des volailles à abattre = " +prixTotal +"€");

    }
}
