package tictactoe;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import tictactoe.Morpion;

public class MorpionStepDefinitions {

    private Morpion morpion;
    private Joueur joueurX;
    private Joueur joueurO;
    private IA ia;

    @Given("un plateau de morpion vide")
    public void un_plateau_de_morpion_vide() {
        morpion = new Morpion();
        joueurX = new Joueur("X");
        joueurO = new Joueur("O");
        ia = new IA(morpion, "O");
    }

    @When("le joueur {string} joue en position ({int}, {int})")
    public void le_joueur_joue_en_position(String joueur, int x, int y) {
        if (joueur.equals("X")) {
            morpion.jouer(joueurX, x, y);
        } else {
            morpion.jouer(joueurO, x, y);
        }
    }

    @Then("le joueur {string} a gagné la partie")
    public void le_joueur_a_gagné_la_partie(String joueur) {
        Joueur gagnant = morpion.getGagnant();
        assertEquals(joueur, gagnant.getNom());
    }

    @When("c'est au tour de l'IA de jouer")
    public void c_est_au_tour_de_l_IA_de_jouer() {
        ia.jouer();
    }

    @Then("l'IA doit jouer en position ({int}, {int})")
    public void l_IA_doit_jouer_en_position(int x, int y) {
        int[] position = ia.getDernierePosition();
        assertEquals(x, position[0]);
        assertEquals(y, position[1]);
    }
}