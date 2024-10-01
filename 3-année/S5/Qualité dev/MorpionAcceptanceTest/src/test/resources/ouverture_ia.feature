Feature: Stratégie d'ouverture par l'IA dans le jeu de morpion
package fr.LucasDaSilveiraMorpion.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;

public class MorpionStepDefinitions {

    @Given("un plateau de morpion vide")
    public void un_plateau_de_morpion_vide() {
        // Code pour initialiser un plateau de morpion vide
    }

    @When("le joueur {string} joue en position ({int}, {int})")
    public void le_joueur_joue_en_position(String joueur, int x, int y) {
        // Code pour gérer le coup d'un joueur à une position donnée
    }

    @Then("le joueur {string} a gagné la partie")
    public void le_joueur_a_gagné_la_partie(String joueur) {
        // Code pour vérifier si le joueur a gagné la partie
    }

    @When("c'est au tour de l'IA de jouer")
    public void c_est_au_tour_de_l_IA_de_jouer() {
        // Code pour gérer le tour de l'IA
    }

    @Then("l'IA doit jouer en position ({int}, {int})")
    public void l_IA_doit_jouer_en_position(int x, int y) {
        // Code pour vérifier que l'IA joue à la position attendue
    }
}
  Scenario: L'IA commence en jouant au centre
    Given un plateau de morpion vide
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (2, 2)

  Scenario: L'IA joue un coin si le centre est occupé
    Given un plateau de morpion avec les coups suivants :
      | position | joueur |
      | (2, 2)   | X      |
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (1, 1) or (1, 3) or (3, 1) or (3, 3)

  Scenario: L'IA commence par un coin si elle joue en premier
    Given un plateau de morpion vide
    When l'IA choisit de commencer dans un coin
    Then l'IA doit jouer en position (1, 1) or (1, 3) or (3, 1) or (3, 3)

  Scenario: L'IA joue le côté si le centre et les coins sont occupés
    Given un plateau de morpion avec les coups suivants :
      | position | joueur |
      | (2, 2)   | X      |
      | (1, 1)   | O      |
      | (1, 3)   | X      |
    When c'est au tour de l'IA de jouer
    Then l'IA doit jouer en position (1, 2) or (2, 1) or (2, 3) or (3, 2)

  Scenario: L'IA commence sur un côté si elle ne joue pas au centre ou dans un coin
    Given un plateau de morpion vide
    When l'IA choisit de ne pas jouer au centre ni dans un coin
    Then l'IA doit jouer en position (1, 2) or (2, 1) or (2, 3) or (3, 2)
