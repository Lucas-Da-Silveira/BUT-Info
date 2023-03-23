package pendu;

import java.util.Scanner;

public class Game {
    private Joueur joueur;
    private Mot mot;

    public Game(Joueur joueur, Mot mot) {
        this.joueur = joueur;
        this.mot = mot;
    }

    public void play() {
        mot.affiche();
        while(!mot.estTrouve() && !joueur.estMort()) {
            char lettre = joueur.proposeLettre(new Scanner(System.in));
            if (mot.contient(lettre)) {
                mot.decouvre(lettre);
                mot.affiche();
            } else {
                joueur.perdVie();
            }
        }
        if (joueur.estMort())
            System.out.println("Ah Ah Ah, le mot "+ mot.getMotCache()+ " a causé ta perte !!!");
        else
            System.out.println("Bravo");
    }

}
