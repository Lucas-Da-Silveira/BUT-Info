package pendu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class Pendu {

    public static void main(String[] args) {
        Dictionnary dico = null;
        try {
            dico = new Dictionnary(new FileReader(new File("resources/francais-divers1.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Joueur joueur = new Joueur();
        Mot mot = Mot.createMot(dico);
        Game jeu = new Game(joueur, mot);
        jeu.play();
    }

    /* première version du main avant refactoring sur le constructeur de la classe Dictionnary */
    /*public static void oldmain(String[] args) {
        Dictionnary dico = new Dictionnary();
        Joueur joueur = new Joueur();
        Mot mot = Mot.createMot(dico);
        Game jeu = new Game(joueur, mot);
        jeu.play();
    }*/
}
