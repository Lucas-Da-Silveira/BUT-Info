import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import tictactoe.Grille;
import tictactoe.Human;
import tictactoe.IA;
import tictactoe.Joueur;

import java.io.StringReader;
import java.util.Random;
import java.util.Scanner;

public class AIOpeningStepDefs {

    @Given("un plateau de morpion vide")

    @When("it's the AI's turn to play")
    public void itSTheAISTurnToPlay() {
        try {
            TestStorage.ia.joue(TestStorage.grille);
        } catch (Exception ignored) {
        }
    }

    @Then("the AI must play in the center")
    public void theAIMustPlayInTheCenter() {
        assert !TestStorage.grille.estLibre(4);
    }

    @And("human played in the center")
    public void playerPlayedInTheCenter() {
        try {
            TestStorage.grille.joue(4, TestStorage.joueur1);
        } catch (Exception ignored) {
        }
    }
    @And("human played in {int}")
    public void humanPlayedIn(int arg0) {
        try {
            TestStorage.grille.joue(arg0, TestStorage.joueur1);
        } catch (Exception ignored) {
        }
    }

    @Then("the AI must play in a corner")
    public void theAIMustPlayInACorner() {
        assert !TestStorage.grille.estLibre(0) || !TestStorage.grille.estLibre(2) || !TestStorage.grille.estLibre(6) || !TestStorage.grille.estLibre(8);
    }

    @Then("the AI must play in a side")
    public void theAIMustPlayInASide() {
        assert !TestStorage.grille.estLibre(1) || !TestStorage.grille.estLibre(3) || !TestStorage.grille.estLibre(5) || !TestStorage.grille.estLibre(7);
    }
}
