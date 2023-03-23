package pendu;

import java.util.Scanner;

public class Joueur {
    private int nbVie;

    public Joueur() {
        nbVie = 10;
    }

    public boolean estMort() {
        return nbVie <= 0;
    }

    public void perdVie() {
        nbVie--;
    }

    public char proposeLettre(Scanner scan) {
        String ligne;
        do {
            ligne = scan.nextLine();
        } while (ligne.length() != 1);
        return ligne.charAt(0);
    }
}
