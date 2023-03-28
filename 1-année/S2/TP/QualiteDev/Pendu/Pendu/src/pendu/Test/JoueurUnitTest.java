package pendu.Test;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import pendu.Joueur;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JoueurUnitTest {

    @Test
    public void testEstMortSurJoueurVivant() {
        //define
        Joueur joueur = new Joueur();
        //when
        boolean estMort = joueur.estMort();
        //then
        Assertions.assertFalse(estMort);

    }

    @Test
    public void testEstMortSurJoueurMort() {
        //define
        Joueur joueur = new Joueur();
        joueur.perdVie();
        joueur.perdVie();
        joueur.perdVie();
        joueur.perdVie();
        joueur.perdVie();
        joueur.perdVie();
        joueur.perdVie();
        joueur.perdVie();
        joueur.perdVie();
        joueur.perdVie();
        //when
        boolean estMort = joueur.estMort();
        //then
        Assertions.assertTrue(estMort);

    }

    /*@pendu.Test
    public void testProposeLettre(){
        Joueur joueur = new Joueur();
        char lettre = joueur.proposeLettre((new Scanner("o")));
        assertEquals("o",lettre);
    }*/
}