import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import tictactoe.*;

import static org.junit.Assert.*;

public class VictoryStepsDef {

    private Grille grille;
    private Joueur joueurX;
    private Joueur joueurO;

    @Given("un plateau de morpion vide")
    public void un_plateau_de_morpion_vide() {
        grille = new Grille();
        joueurX = new IA("X");
        joueurO = new IA("O");
    }

    @When("le joueur X joue en position \\({int}, {int})")
    public void le_joueur_x_joue_en_position(Integer ligne, Integer colonne) throws OccupiedCellException {
        grille.joue((ligne - 1) * 3 + (colonne - 1), joueurX);
    }

    @When("le joueur O joue en position \\({int}, {int})")
    public void le_joueur_o_joue_en_position(Integer ligne, Integer colonne) throws OccupiedCellException {
        grille.joue((ligne - 1) * 3 + (colonne - 1), joueurO);
    }

    @When("l'ia commence en jouant au centre")
    public void l_ia_commence_en_jouant_au_centre() throws OccupiedCellException {
        grille.joue(4, joueurX);
    }

    @Then("le joueur X a gagné la partie")
    public void le_joueur_x_a_gagne_la_partie() {
        assertEquals(joueurX, grille.gagnant());
    }

    @Then("le joueur O a gagné la partie")
    public void le_joueur_o_a_gagne_la_partie() {
        assertEquals(joueurO, grille.gagnant());
    }
}
