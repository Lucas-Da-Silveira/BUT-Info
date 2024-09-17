Feature: Vérification de la victoire dans le jeu de morpion

  Background:
    Given un plateau de morpion vide

  Scenario: Joueur X gagne avec une ligne horizontale contre le joueur O
    When le joueur X joue en position (1, 1)
    And le joueur O joue en position (2, 1)
    And le joueur X joue en position (1, 2)
    And le joueur O joue en position (2, 2)
    And le joueur X joue en position (1, 3)
    Then le joueur X a gagné la partie

  Scenario: Joueur O gagne avec une ligne verticale contre le joueur X
    Given un plateau de morpion vide
    When le joueur X joue en position (1, 2)
    And le joueur O joue en position (1, 1)
    And le joueur X joue en position (2, 2)
    And le joueur O joue en position (2, 1)
    And le joueur X joue en position (3, 3)
    And le joueur O joue en position (3, 1)
    Then le joueur O a gagné la partie

  Scenario: Joueur X gagne avec une diagonale contre le joueur O
    Given un plateau de morpion vide
    When le joueur X joue en position (1, 1)
    And le joueur O joue en position (1, 2)
    And le joueur X joue en position (2, 2)
    And le joueur O joue en position (3, 1)
    And le joueur X joue en position (3, 3)
    Then le joueur X a gagné la partie
